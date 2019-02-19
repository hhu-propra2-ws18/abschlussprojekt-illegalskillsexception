package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.*;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
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
        ApplicationUser applicationUser2 = mock(ApplicationUser.class);
        ApplicationUser applicationUser3 = mock(ApplicationUser.class);
        ApplicationUser applicationUser4 = mock(ApplicationUser.class);
        when(applicationUser0.getId()).thenReturn(0L);
        when(applicationUser1.getId()).thenReturn(1L);
        when(applicationUser2.getId()).thenReturn(2L);
        when(applicationUser3.getId()).thenReturn(3L);
        when(applicationUser4.getId()).thenReturn(4L);

        Article article = mock(Article.class);
        when(article.getLocation()).thenReturn("Düsseldorf");
        when(article.getTitle()).thenReturn("Title");

        LendingPeriod lendingPeriod0 = new LendingPeriod(LocalDate.of(2019, 2, 3), LocalDate.of(2019, 2, 5));
        LendingPeriod lendingPeriod1 = new LendingPeriod(LocalDate.of(2019, 2, 3), LocalDate.of(2019, 2, 10));

        LocalDateTime timestamp = LocalDateTime.of(2019, 2, 3, 12, 23,34);

        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        Inquiry inquiry2 = new Inquiry();
        Inquiry inquiry3 = new Inquiry();
        Inquiry inquiry4 = new Inquiry();
        inquiry0.setArticle(article);
        inquiry1.setArticle(article);
        inquiry2.setArticle(article);
        inquiry3.setArticle(article);
        inquiry4.setArticle(article);
        inquiry0.setId(0L);
        inquiry1.setId(1L);
        inquiry2.setId(2L);
        inquiry3.setId(3L);
        inquiry4.setId(4L);
        inquiry0.setStatus(Inquiry.Status.open);
        inquiry1.setStatus(Inquiry.Status.accepted);
        inquiry0.setBorrower(applicationUser0);
        inquiry1.setBorrower(applicationUser1);
        inquiry2.setBorrower(applicationUser3);
        inquiry3.setBorrower(applicationUser3);
        inquiry4.setBorrower(applicationUser3);
        inquiry0.setLender(applicationUser0);
        inquiry1.setLender(applicationUser1);
        inquiry2.setLender(applicationUser4);
        inquiry3.setLender(applicationUser4);
        inquiry4.setLender(applicationUser4);
        inquiry0.setDuration(lendingPeriod0);
        inquiry1.setDuration(lendingPeriod1);
        inquiry0.setTimestamp(timestamp);
        inquiry1.setTimestamp(timestamp.plusMinutes(2));

        inquiryList = new ArrayList<>();
        inquiryList.addAll(Arrays.asList(inquiry0, inquiry1, inquiry2, inquiry3, inquiry4));

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

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setStatus(Transaction.Status.conflict);
        transaction2.setReturnDate(LocalDate.of(2019, 2, 2));
        transaction2.setInquiry(inquiry2);
        transaction2.setTimestamp(timestamp.plusMinutes(2));
        transaction2.setUpdated(timestamp.plusMinutes(2));

        Transaction transaction3 = new Transaction();
        transaction3.setId(3L);
        transaction3.setStatus(Transaction.Status.conflict);
        transaction3.setReturnDate(LocalDate.of(2019, 2, 2));
        transaction3.setInquiry(inquiry3);
        transaction3.setTimestamp(timestamp.plusMinutes(2));
        transaction3.setUpdated(timestamp.plusMinutes(2));

        Transaction transaction4 = new Transaction();
        transaction4.setId(4L);
        transaction4.setStatus(Transaction.Status.conflict);
        transaction4.setReturnDate(LocalDate.of(2019, 2, 2));
        transaction4.setInquiry(inquiry4);
        transaction4.setTimestamp(timestamp.plusMinutes(2));
        transaction4.setUpdated(timestamp.plusMinutes(2));

        transactionList = new ArrayList<>();
        transactionList.addAll(Arrays.asList(transaction0, transaction1, transaction2, transaction3, transaction4));

        transactionRepository = mock(TransactionRepository.class);
        when(transactionRepository.existsById(0L)).thenReturn(true);
        when(transactionRepository.existsById(1L)).thenReturn(false);
        when(transactionRepository.findById(0L)).thenReturn(Optional.of(new Transaction()));
        when(transactionRepository.save(transaction0)).thenReturn(transaction0);
        when(transactionRepository.save(transaction1)).thenReturn(transaction1);
        when(transactionRepository.findAll()).thenReturn(transactionList);

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

    @Test
    public void getExistingTransaction() {
        assertNotNull(transactionService.getTransaction(0L));
        verify(transactionRepository).existsById(0L);
    }

    @Test
    public void getNullFromInexistentTransaction() {
        assertNull(transactionService.getTransaction(1L));
        verify(transactionRepository).existsById(1L);
    }

    @Test
    public void getAllTransactionsForUser() {
        List<Transaction> expected = new ArrayList<>();
        expected.add(transactionList.get(0));
        assertEquals(expected, transactionService.getAllTransactionsForUser(0L));
        verify(transactionRepository).findAll();
    }

    @Test
    public void getNoTransactionFromUser() {
        assertEquals(new ArrayList<>(), transactionService.getAllTransactionsForUser(2L));
        verify(transactionRepository).findAll();
    }

    @Test
    public void getTransactionsOnlyBorrower() {
        List<Transaction> expected = new ArrayList<>();
        expected.add(transactionList.get(2));
        expected.add(transactionList.get(3));
        expected.add(transactionList.get(4));
        assertEquals(expected, transactionService.getAllTransactionsForUser(3L));
    }

    @Test
    public void getTransactionsOnlyLender() {
        List<Transaction> expected = new ArrayList<>();
        expected.add(transactionList.get(2));
        expected.add(transactionList.get(3));
        expected.add(transactionList.get(4));
        assertEquals(expected, transactionService.getAllTransactionsForUser(4L));
    }

    @Test
    public void exportTransactionsLenderTrue() {
        List<TransactionResponseExport> exportList = new ArrayList<>();
        TransactionResponseExport export = new TransactionResponseExport();
        export.setLender(true);
        export.setLocation("Düsseldorf");
        export.setStatus("open");
        export.setTitle("Title");
        export.setReturnDate(LocalDate.of(2019, 1, 1));
        exportList.add(export);
        assertEquals(exportList, transactionService.exportTransactions(0L));
    }

    @Test
    public void exportTransactionsLenderFalse() {
        List<TransactionResponseExport> exportList = new ArrayList<>();
        TransactionResponseExport export = new TransactionResponseExport();
        export.setLender(false);
        export.setLocation("Düsseldorf");
        export.setStatus("conflict");
        export.setTitle("Title");
        export.setReturnDate(LocalDate.of(2019, 2, 2));
        exportList.add(export);
        exportList.add(export);
        exportList.add(export);
        assertEquals(exportList, transactionService.exportTransactions(3L));
    }
}