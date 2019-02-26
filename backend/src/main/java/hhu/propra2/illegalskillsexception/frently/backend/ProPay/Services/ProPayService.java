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
    public ProPayAccount createAccount(String userName, double amount) {
        String url = BASE_URL + "account/" + userName + "?amount=" + amount;
        ProPayAccount newAccount = restTemplate.postForObject(url, null, ProPayAccount.class);
        moneyTransferService.createMoneyTransfer(userName, userName, amount);
        return newAccount;
    }

    @Override
    public double getAccountBalance(String username) throws ProPayConnectionException {
        ProPayAccount proPayAccount = getProPayAccount(username);
        return proPayAccount.getAmount();
    }

    @Override
    public void payInMoney(String userName, double amount) {
        createAccount(userName, amount);
    }

    @Override
    public void transferMoney(String borrower, String lender, double amount) throws InsuffientFundsException, ProPayConnectionException {
        checkFunds(borrower, amount);
        final String url = BASE_URL + "account/" + borrower + "/transfer/" + lender + "?amount=" + amount;
        try {
            restTemplate.postForLocation(url, null); //TODO: timeout
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
    public ProPayAccount getProPayAccount(String username) throws ProPayConnectionException {
        final String url = BASE_URL + "account/" + username;
        try {
            return restTemplate.getForObject(url, ProPayAccount.class);
        } catch (Exception e) {
            throw new ProPayConnectionException();
        }
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
    public Long blockDeposit(String borrower, String lender, double amount)
            throws ProPayConnectionException, InsuffientFundsException {
        checkFunds(borrower, amount);

        final String url = BASE_URL + "reservation/reserve/" + borrower + "/" + lender + "?amount=" + amount;
        try {
            Reservation reservation = restTemplate.postForObject(url, null, Reservation.class); //TODO: timeout
            return reservation.getId();
        } catch (Exception e) {
            throw new ProPayConnectionException();
        }
    }

    @Override
    public void freeDeposit(String borrower, Transaction transaction) {
        long reservationId = transaction.getReservationId();
        final String url = BASE_URL + "reservation/release/" + borrower + "?reservationId=" + reservationId;
        restTemplate.postForObject(url, null, Reservation.class);
    }

    @Override
    public void punishUser(String borrower, Transaction transaction) {
        long reservationId = transaction.getReservationId();
        final String url = BASE_URL + "reservation/punish/" + borrower + "?reservationId=" + reservationId;
        restTemplate.postForObject(url, null, Reservation.class);
        moneyTransferService.createMoneyTransfer(borrower, transaction.getInquiry().getLender().getUsername(), transaction.getInquiry().getArticle().getDeposit());
    }

    private void checkFunds(String userName, double amount) throws InsuffientFundsException, ProPayConnectionException {
        ProPayAccount proPayAccount = getProPayAccount(userName);
        List<Reservation> reservations = proPayAccount.getReservations();
        double accountBalance = proPayAccount.getAmount();
        if (!amountGreaterThanReservation(reservations, amount, accountBalance)) throw new InsuffientFundsException();
    }
}
