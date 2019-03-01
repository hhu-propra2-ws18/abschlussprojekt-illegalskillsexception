package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConflictServiceTest {

    private ITransactionRepository transactionRepository;
    private List<Transaction> transactions = new ArrayList<>();
    private ConflictService conflictService;

    @Before
    public void setup() {
        transactionRepository = mock(ITransactionRepository.class);
        when(transactionRepository.findAll()).thenReturn(transactions);
        conflictService = new ConflictService(transactionRepository);
    }

    @Test
    public void retrieveAllConflictingTransactionsZeroTransactions() {
        Transaction transaction = new Transaction();
        transaction.setStatus(Transaction.Status.OPEN);
        transactions.add(transaction);
        List<Transaction> results = conflictService.retrieveAllConflictingTransactions();
        assertEquals(0, results.size());
    }

    @Test
    public void retrieveAllConflictingTransactionsOneTransaction() {
        Transaction transaction1 = new Transaction();
        transaction1.setStatus(Transaction.Status.OPEN);
        Transaction transaction2 = new Transaction();
        transaction2.setStatus(Transaction.Status.CONFLICT);
        transactions.add(transaction1);
        transactions.add(transaction2);
        List<Transaction> results = conflictService.retrieveAllConflictingTransactions();
        assertEquals(1, results.size());
    }

    @Test
    public void multipleTransactions() {
        Transaction transaction1 = new Transaction();
        transaction1.setStatus(Transaction.Status.OPEN);
        for (int i = 0; i < 50; i++) {
            Transaction transaction = new Transaction();
            transaction.setStatus(Transaction.Status.CONFLICT);
            transactions.add(transaction);
        }
        transactions.add(transaction1);
        List<Transaction> results = conflictService.retrieveAllConflictingTransactions();
        assertEquals(50, results.size());
    }
}