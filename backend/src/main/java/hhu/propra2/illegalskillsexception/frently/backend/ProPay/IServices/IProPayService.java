package hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.ProPayAccount;

import java.util.List;

public interface IProPayService {
    ProPayAccount createAccount(String userName, double amount);

    double getAccountBalance(String username);

    void payInMoney(String userName, double amount);

    void transferMoney(String borrower, String lender, double amount);

    List<MoneyTransfer> getAllMoneyTransfers(String userName);

    boolean hasEnoughMoney(String userName, double amount);

    Long blockDeposit(String borrower, String lender, double amount) throws Exception;

    void freeDeposit(String borrower, Transaction transaction);

    void punishUser(String borrower, Transaction transaction);
}
