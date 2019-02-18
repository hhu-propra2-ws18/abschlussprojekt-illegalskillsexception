package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Transaction updateTransactionReturnDate(Transaction t, LocalDate date) {
        if (transactionRepository.existsById(t.getId())) {
            t.setReturnDate(date);
            return transactionRepository.save(t);
        }
        return null;
    }

    public Transaction updateTransactionStatus(Transaction t, Transaction.Status status) {
        if (transactionRepository.existsById(t.getId())) {
            t.setStatus(status);
            return transactionRepository.save(t);
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransaction(long id) {
        if(transactionRepository.existsById(id)){
            if (transactionRepository.findById(id).isPresent())
                return transactionRepository.findById(id).get();
        }
        return null;
    }

    private Transaction setTransaction(Transaction temp, Inquiry inquiry, Transaction.Status status, LocalDate returnDate) {
        temp.setInquiry(inquiry);
        temp.setStatus(status);
        temp.setReturnDate(returnDate);
        return temp;
    }
}
