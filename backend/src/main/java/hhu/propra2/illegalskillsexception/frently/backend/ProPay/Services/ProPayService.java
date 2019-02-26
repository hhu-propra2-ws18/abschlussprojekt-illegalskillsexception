package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IMoneyTransferService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.ProPayAccount;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProPayService implements IProPayService {
    private String BASE_URL;
    private IMoneyTransferService moneyTransferService;
    private RestTemplate restTemplate = new RestTemplate();

    //service.url takes the url from the application.properties based on the spring-configuration
    @Autowired
    public ProPayService(IMoneyTransferService moneyTransferService, @Value("${service.url}") String BASE_URL) {
        this.moneyTransferService = moneyTransferService;
        this.BASE_URL = BASE_URL;
    }

    @Override
    @Retryable(value = {ProPayConnectionException.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    public ProPayAccount createAccount(String userName, double amount) throws ProPayConnectionException {
        String url = BASE_URL + "account/" + userName + "?amount=" + amount;
        ProPayAccount newAccount;
        try {
            newAccount = restTemplate.postForObject(url, null, ProPayAccount.class);
        } catch (Exception e) {
            throw new ProPayConnectionException();
        }
        if (newAccount == null) {
            throw new ProPayConnectionException();
        }
        moneyTransferService.createMoneyTransfer(userName, userName, amount);
        return newAccount;
    }

    @Override
    public double getAccountBalance(String username) throws ProPayConnectionException {
        ProPayAccount proPayAccount = getProPayAccount(username);
        return proPayAccount.getAmount();
    }

    @Override
    public void payInMoney(String userName, double amount) throws ProPayConnectionException {
        createAccount(userName, amount);
    }

    @Override
    @Retryable(value = {ProPayConnectionException.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    public void transferMoney(String borrower, String lender, double amount) throws InsuffientFundsException, ProPayConnectionException {
        checkFunds(borrower, amount);
        final String url = BASE_URL + "account/" + borrower + "/transfer/" + lender + "?amount=" + amount;
        try {
            restTemplate.postForLocation(url, null);
        } catch (Exception e) {
            throw new ProPayConnectionException();
        }
        moneyTransferService.createMoneyTransfer(borrower, lender, amount);
    }

    @Override
    public List<MoneyTransfer> getAllMoneyTransfers(String userName) {
        return moneyTransferService.getAll(userName);
    }

    @Override
    @Retryable(value = {ProPayConnectionException.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    public ProPayAccount getProPayAccount(String username) throws ProPayConnectionException {
        final String url = BASE_URL + "account/" + username;
        ProPayAccount account;
        try {
            account = restTemplate.getForObject(url, ProPayAccount.class);
        } catch (Exception e) {
            throw new ProPayConnectionException();
        }
        if (account == null) {
            throw new ProPayConnectionException();
        }
        return account;
    }

    @Override
    public boolean amountGreaterThanReservation(List<Reservation> reservations, double amount, double accountBalance) {
        double alreadyReserved = 0;
        for (Reservation reservation : reservations) {
            alreadyReserved += reservation.getAmount();
        }
        return accountBalance >= (amount + alreadyReserved);
    }

    @Override
    public Long blockDeposit(String borrower, String lender, double amount) throws ProPayConnectionException, InsuffientFundsException {
        checkFunds(borrower, amount);
        final String url = BASE_URL + "reservation/reserve/" + borrower + "/" + lender + "?amount=" + amount;
        Reservation reservation = postForReservation(url);
        return reservation.getId();
    }

    @Retryable(value = {ProPayConnectionException.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    private Reservation postForReservation(String url) throws ProPayConnectionException {
        Reservation reservation;
        try {
            reservation = restTemplate.postForObject(url, null, Reservation.class);
        } catch (Exception e) {
            throw new ProPayConnectionException();
        }
        if (reservation == null) {
            throw new ProPayConnectionException();
        }
        return reservation;
    }

    @Override
    @Retryable(value = {ProPayConnectionException.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    public void freeDeposit(String borrower, Transaction transaction) throws ProPayConnectionException {
        long reservationId = transaction.getReservationId();
        final String url = BASE_URL + "reservation/release/" + borrower + "?reservationId=" + reservationId;
        postForReservation(url);
    }

    @Override
    @Retryable(value = {ProPayConnectionException.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    public void punishUser(String borrower, Transaction transaction) throws ProPayConnectionException {
        long reservationId = transaction.getReservationId();
        final String url = BASE_URL + "reservation/punish/" + borrower + "?reservationId=" + reservationId;
        postForReservation(url);
        moneyTransferService.createMoneyTransfer(borrower, transaction.getInquiry().getLender().getUsername(), transaction.getInquiry().getArticle().getDeposit());
    }

    @Retryable(value = {ProPayConnectionException.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    private void checkFunds(String userName, double amount) throws InsuffientFundsException, ProPayConnectionException {
        ProPayAccount proPayAccount = getProPayAccount(userName);
        List<Reservation> reservations = proPayAccount.getReservations();
        double accountBalance = proPayAccount.getAmount();
        if (!amountGreaterThanReservation(reservations, amount, accountBalance)) throw new InsuffientFundsException();
    }

    //Recover Methods for Retryable
    @Recover
    private Reservation recoverPostForReservation(ProPayConnectionException e, String url) {
        return null;
    }

    @Recover
    private ProPayAccount recoverGetProPayAccount(ProPayConnectionException e, String userName) {
        return null;
    }

    @Recover
    private ProPayAccount recoverCreateAccount(ProPayConnectionException e, String userName, double amount) {
        return null;
    }
}
