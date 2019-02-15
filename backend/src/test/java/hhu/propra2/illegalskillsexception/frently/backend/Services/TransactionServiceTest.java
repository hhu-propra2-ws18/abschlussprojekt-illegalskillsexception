package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.LendingPeriod;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    private TransactionRepository transactionRepository;
    private TransactionService transactionService;
    private ArrayList<Transaction> transactionList;
    private ArrayList<Inquiry> inquiryList;

    @Before
    public void setup() {
        ApplicationUser applicationUser0 = mock(ApplicationUser.class);
        ApplicationUser applicationUser1 = mock(ApplicationUser.class);
        when(applicationUser0.getId()).thenReturn(0L);
        when(applicationUser1.getId()).thenReturn(1L);

        LendingPeriod lendingPeriod0 = new LendingPeriod(LocalDate.of(2019, 2, 3), LocalDate.of(2019, 2, 5));
        LendingPeriod lendingPeriod1 = new LendingPeriod(LocalDate.of(2019, 2, 3), LocalDate.of(2019, 2, 10));

        LocalDateTime timestamp = LocalDateTime.of(2019, 2, 3, 12, 23,34);

        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        inquiry0.setId(0L);
        inquiry1.setId(1L);
        inquiry0.setStatus(Inquiry.Status.open);
        inquiry1.setStatus(Inquiry.Status.accepted);
        inquiry0.setBorrower(applicationUser0);
        inquiry1.setBorrower(applicationUser1);
        inquiry0.setLender(applicationUser0);
        inquiry1.setLender(applicationUser1);
        inquiry0.setDuration(lendingPeriod0);
        inquiry1.setDuration(lendingPeriod1);
        inquiry0.setTimestamp(timestamp);
        inquiry1.setTimestamp(timestamp.plusMinutes(2));

        inquiryList = new ArrayList<>();
        inquiryList.addAll(Arrays.asList(inquiry0, inquiry1));

        Transaction transaction0 = new Transaction();
        transaction0.setId(0L);
        transaction0.setStatus(Transaction.Status.open);
        transaction0.setReturnDate(LocalDate.of(2019, 1, 1));
        transaction0.setInquiry(inquiry0);
        transaction0.setTimestamp(timestamp);
        transaction0.setUpdated(timestamp);

        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setStatus(Transaction.Status.conflict);
        transaction1.setReturnDate(LocalDate.of(2019, 2, 2));
        transaction1.setInquiry(inquiry1);
        transaction1.setTimestamp(timestamp.plusMinutes(2));
        transaction1.setUpdated(timestamp.plusMinutes(2));

        transactionList = new ArrayList<>();
        transactionList.addAll(Arrays.asList(transaction0, transaction1));

        transactionRepository = mock(TransactionRepository.class);
        when(transactionRepository.existsById(0L)).thenReturn(true);
        when(transactionRepository.existsById(1L)).thenReturn(false);
        when(transactionRepository.save(transaction0)).thenReturn(transaction0);
        when(transactionRepository.save(transaction1)).thenReturn(transaction1);

        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void updateTransactionReturnDateIfTransactionExistsAndReturnUpdatedObject() {
        Transaction testTransaction = transactionList.get(0);
        LocalDate newDate = LocalDate.of(2019, 12, 12);

        Transaction updatedTransaction = transactionService.updateTransactionReturnDate(testTransaction, newDate);
        LocalDate updatedDate = updatedTransaction.getReturnDate();

        verify(transactionRepository).save(updatedTransaction);
        assertEquals(newDate.getYear(), updatedDate.getYear());
        assertEquals(newDate.getDayOfYear(), updatedDate.getDayOfYear());
    }

    @Test
    public void updateTransactionReturnDateIfTransactionDoesNotExistAndReturnNull() {
        Transaction testTransaction = transactionList.get(1);
        LocalDate newDate = LocalDate.of(2019, 12, 12);

        assertNull(transactionService.updateTransactionReturnDate(testTransaction, newDate));
        verify(transactionRepository, never()).save(testTransaction);
    }

    @Test
    public void updateTransactionStatusIfTransactionExistsAndReturnUpdatedObject() {
        Transaction testTransaction = transactionList.get(0);

        Transaction updatedTransaction = transactionService.updateTransactionStatus(testTransaction, Transaction.Status.closed);

        verify(transactionRepository).save(updatedTransaction);
        assertEquals(Transaction.Status.closed, updatedTransaction.getStatus());
    }

    @Test
    public void updateTransactionStatusIfTransactionDoesNotExistAndReturnNull() {
        Transaction testTransaction = transactionList.get(1);

        assertNull(transactionService.updateTransactionStatus(testTransaction, Transaction.Status.closed));
        verify(transactionRepository, never()).save(testTransaction);
    }
}