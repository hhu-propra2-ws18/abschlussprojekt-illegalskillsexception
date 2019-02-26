package hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.ProPayAccount;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.Reservation;

import java.util.List;

public interface IProPayService {
    ProPayAccount createAccount(String userName, double amount) throws ProPayConnectionException;

    double getAccountBalance(String username) throws ProPayConnectionException;

    void payInMoney(String userName, double amount) throws ProPayConnectionException;

    void transferMoney(String borrower, String lender, double amount) throws InsuffientFundsException, ProPayConnectionException;

    List<MoneyTransfer> getAllMoneyTransfers(String userName);

    ProPayAccount getProPayAccount(String username) throws ProPayConnectionException;

    boolean amountGreaterThanReservation(List<Reservation> reservations, double amount, double accountBalance);

    Long blockDeposit(String borrower, String lender, double amount) throws ProPayConnectionException, InsuffientFundsException;

    void freeDeposit(String borrower, Transaction transaction) throws ProPayConnectionException;

    void punishUser(String borrower, Transaction transaction) throws ProPayConnectionException;
}
