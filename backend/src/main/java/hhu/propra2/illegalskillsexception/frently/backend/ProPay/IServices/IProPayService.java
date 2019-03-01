package hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsufficientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.ProPayAccount;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.Reservation;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IProPayService {
    ProPayAccount createAccount(String userName, double amount) throws ProPayConnectionException, ExhaustedRetryException, UserNotFoundException;

    double getAccountBalance(String username) throws ProPayConnectionException, ExhaustedRetryException;

    void payInMoney(Authentication auth, double amount) throws ProPayConnectionException, ExhaustedRetryException, UserNotFoundException;

    void transferMoney(String borrower, String lender, double amount) throws InsufficientFundsException, ProPayConnectionException, ExhaustedRetryException, UserNotFoundException;

    List<MoneyTransfer> getAllMoneyTransfers(String userName);

    ProPayAccount getProPayAccount(String username) throws ProPayConnectionException, ExhaustedRetryException;

    boolean amountGreaterThanReservation(List<Reservation> reservations, double amount, double accountBalance);

    Long blockDeposit(String borrower, String lender, double amount) throws ProPayConnectionException, InsufficientFundsException, ExhaustedRetryException;

    void freeDeposit(String borrower, Transaction transaction) throws ProPayConnectionException;

    void punishUser(String borrower, Transaction transaction) throws ProPayConnectionException, UserNotFoundException;
}
