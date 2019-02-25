package hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.ProPayAccount;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.Reservation;

import java.util.List;

public interface IProPayService {
    ProPayAccount createAccount(String userName, double amount);

    double getAccountBalance(String username);

    void payInMoney(String userName, double amount);

    void transferMoney(String borrower, String lender, double amount);

    List<MoneyTransfer> getAllMoneyTransfers(String userName);

    ProPayAccount getProPayAccount(String username);

    boolean hasEnoughMoney(String userName, double amount);

    boolean amountGreaterThanReservation(List<Reservation> reservations, double amount, double accountBalance);

    Long blockDeposit(String borrower, String lender, double amount) throws ProPayException;

    void freeDeposit(String borrower, Transaction transaction);

    void punishUser(String borrower, Transaction transaction);
}
