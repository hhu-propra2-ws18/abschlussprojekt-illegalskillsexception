package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository pTransactionRepo) {
        this.transactionRepository = pTransactionRepo;
    }

    public void createTransaction(Transaction.Status status, Inquiry inquiry) {
        final Transaction temp = setTransaction(new Transaction(), inquiry, Transaction.Status.open, null);
        transactionRepository.save(temp);
    }

    public void updateTransactionReturnDate(Transaction t, LocalDate date) {
        if (transactionRepository.existsById(t.getId())) {
            t.setReturnDate(date);
            transactionRepository.save(t);
        }
    }

    public void updateTransactionStatus(Transaction t, Transaction.Status status) {
        if (transactionRepository.existsById(t.getId())) {
            t.setStatus(status);
            transactionRepository.save(t);
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactions(long id) {
        if(transactionRepository.existsById(id)){
            return Collections.singletonList(transactionRepository.findById(id).get());
        } else {
            return null;
        }
    }

    private Transaction setTransaction(Transaction temp, Inquiry inquiry, Transaction.Status status, LocalDate returnDate) {
        temp.setInquiry(inquiry);
        temp.setStatus(status);
        temp.setReturnDate(returnDate);
        return temp;
    }
}
