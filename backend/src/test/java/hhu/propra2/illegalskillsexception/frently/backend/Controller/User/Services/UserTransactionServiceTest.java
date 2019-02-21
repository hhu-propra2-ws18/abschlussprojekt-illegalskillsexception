package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTransactionServiceTest {

    private ITransactionRepository repository;

    private List<Transaction> idOneSolution;
    private List<Transaction> idZeroSolution;
    private List<Transaction> idNotFoundSolution;

    private static ApplicationUser createUser(long id) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(id);
        return applicationUser;
    }

    private static Transaction createTransaction(long borrowUserId, long lendUserId, Transaction.Status status, long transactionId) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionId);

        Inquiry inquiry = new Inquiry();

        ApplicationUser borrower = createUser(borrowUserId);
        ApplicationUser lender = createUser(lendUserId);

        inquiry.setBorrower(borrower);
        inquiry.setLender(lender);

        transaction.setInquiry(inquiry);
        transaction.setStatus(status);
        return transaction;
    }

    @Before
    public void setup() {
        repository = mock(ITransactionRepository.class);

        Transaction first = createTransaction(0, 1, Transaction.Status.closed, 1);
        Transaction second = createTransaction(1321, 1, Transaction.Status.closed, 2);
        Transaction third = createTransaction(4, 0, Transaction.Status.open, 3);
        Transaction fourth = createTransaction(1, 2, Transaction.Status.conflict, 4);

        when(repository.findAll()).thenReturn(
                Arrays.asList(first, second, third, fourth)
        );

        idZeroSolution = Arrays.asList(first);
        idOneSolution = Arrays.asList(first, second);
        idNotFoundSolution = Arrays.asList();
    }

    @Test
    public void getAllFinishedTransactions_IdOne() {
        UserTransactionService service = new UserTransactionService(repository);

        List<Transaction> result = service.getAllFinishedTransactions(createUser(1));

        Assert.assertEquals(result, idOneSolution);
    }

    @Test
    public void getAllFinishedTransactions_IdZero() {
        UserTransactionService service = new UserTransactionService(repository);

        List<Transaction> result = service.getAllFinishedTransactions(createUser(0));

        Assert.assertEquals(result, idZeroSolution);
    }

    @Test
    public void getAllFinishedTransactions_IdNotFound() {
        UserTransactionService service = new UserTransactionService(repository);

        List<Transaction> result = service.getAllFinishedTransactions(createUser(-1));

        Assert.assertEquals(result, idNotFoundSolution);
    }
}
