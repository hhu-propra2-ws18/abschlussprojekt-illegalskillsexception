package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.MoneyTransfer;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ProPayAccount;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Reservation;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProPayService {

    private IMoneyTransferService moneyTransferService;
    private IReservationService reservationService;

    @Autowired
    public ProPayService(IMoneyTransferService moneyTransferService, IReservationService reservationService) {
        this.moneyTransferService = moneyTransferService;
        this.reservationService = reservationService;
    }

    public double getAccountBalance(String username) {
        ProPayAccount proPayAccount = getProPayAccount(username);
        return proPayAccount.getAmount();
    }

    public void payInMoney(String userName, double amount) {
        final String url = "http://localhost:8080/propay/account/" + userName + "?amount=" + amount;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(url, null);
        moneyTransferService.createMoneyTransfer(userName, userName, amount);
    }

    private ProPayAccount getProPayAccount(String username) {
        final String url = "http://localhost:8080/ProPay/account/" + username;
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, ProPayAccount.class);
    }

    public boolean hasEnoughMoney(String userName, double amount) {
        double userBalance = getAccountBalance(userName);
        return userBalance >= amount;
    }
    //public void freeDeposit(String userName,)

    public void blockDeposit(String borrower, String lender, double amount, Transaction transaction) {
        final String url = "http://localhost:8080/Propay/reservation/reserve/" + borrower + "/" + lender + "?amount=" + amount;
        RestTemplate restTemplate = new RestTemplate();
        Reservation reservation = restTemplate.postForObject(url, null, Reservation.class);
        reservationService.save(reservation);
        transaction.setReservationId(reservation.getPropayId());

    }

    public void transferMoney(String borrower, String lender, double amount) {
        final String url = "http://localhost:8080/Propay/account/" + borrower + "/transfer/" + lender + "?amount=" + amount;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(url, null);
        moneyTransferService.createMoneyTransfer(borrower, lender, amount);
    }

    public List<MoneyTransfer> getAllMoneyTransfers(String userName) {
        return moneyTransferService.getAll(userName);
    }
}
