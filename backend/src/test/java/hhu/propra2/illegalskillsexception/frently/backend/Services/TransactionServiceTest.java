package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TransactionServiceTest {
    private TransactionRepository transactionRepository;
    private TransactionService transactionService;
    private ArrayList<Transaction> transactionList;
    private ArrayList<Inquiry> inquiryList;

    @Before
    public void setup() {
        Inquiry inquiry0 = mock(Inquiry.class);
        Inquiry inquiry1 = mock(Inquiry.class);
        Inquiry inquiry2 = mock(Inquiry.class);
        when(inquiry0.getId()).thenReturn((long) 0);
        when(inquiry1.getId()).thenReturn((long) 0);
        when(inquiry2.getId()).thenReturn((long) 0);

        inquiryList = new ArrayList<>();
        inquiryList.addAll(Arrays.asList(inquiry0, inquiry1, inquiry2));

        LocalDateTime tmpTimeStamp = LocalDateTime.now();

        Transaction transaction0 = mock(Transaction.class);
        when(transaction0.getId()).thenReturn((long) 0);
        when(transaction0.getInquiry()).thenReturn(inquiry0);
        when(transaction0.getReturnDate()).thenReturn(LocalDate.now().plusDays(3));
        when(transaction0.getTimestamp()).thenReturn(tmpTimeStamp);
        when(transaction0.getUpdated()).thenReturn(tmpTimeStamp);
        when(transaction0.getStatus()).thenReturn(Transaction.Status.open);

        Transaction transaction1 = mock(Transaction.class);
        when(transaction1.getId()).thenReturn((long) 1);
        when(transaction1.getInquiry()).thenReturn(inquiry1);
        when(transaction1.getReturnDate()).thenReturn(LocalDate.now().plusDays(7));
        when(transaction1.getTimestamp()).thenReturn(tmpTimeStamp);
        when(transaction1.getUpdated()).thenReturn(tmpTimeStamp);
        when(transaction1.getStatus()).thenReturn(Transaction.Status.closed);

        Transaction transaction2 = mock(Transaction.class);
        when(transaction2.getId()).thenReturn((long) 2);
        when(transaction2.getInquiry()).thenReturn(inquiry2);
        when(transaction2.getReturnDate()).thenReturn(LocalDate.now().plusDays(1));
        when(transaction2.getTimestamp()).thenReturn(tmpTimeStamp);
        when(transaction2.getUpdated()).thenReturn(tmpTimeStamp);
        when(transaction2.getStatus()).thenReturn(Transaction.Status.conflict);

        transactionList = new ArrayList<>();
        transactionList.addAll(Arrays.asList(transaction0, transaction1, transaction2));

        transactionRepository = mock(TransactionRepository.class);
        when(transactionRepository.findAll()).thenReturn(transactionList);

        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void createTransaction() {
        Transaction tmpTransaction = transactionList.get(0);

        transactionService.createTransaction(tmpTransaction.getStatus(), tmpTransaction.getInquiry());
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    public void updateTransactionReturnDate() {
    }

    @Test
    public void updateTransactionStatus() {
    }

    @Test
    public void getAllTransactions() {
    }
}