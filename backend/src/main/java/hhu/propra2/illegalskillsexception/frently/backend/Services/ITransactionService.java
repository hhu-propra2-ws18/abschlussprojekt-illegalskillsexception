package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    void createTransaction(Transaction.Status status, Inquiry inquiry);
    void updateTransactionReturnDate(Transaction t, LocalDate date);
    void updateTransactionStatus(Transaction t, Transaction.Status status);
    List<Transaction> getAllTransactions();
}
