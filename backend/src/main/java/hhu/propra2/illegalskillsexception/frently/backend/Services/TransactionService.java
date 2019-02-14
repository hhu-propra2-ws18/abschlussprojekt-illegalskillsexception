package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository pTransactionRepo) {
        this.transactionRepository = pTransactionRepo;
    }

    public void createTransaction(Transaction.Status status, Inquiry inquiry) {
        final Transaction temp = setTransaction(new Transaction(), inquiry, Transaction.Status.open, null, LocalDateTime.now());
        transactionRepository.save(temp);
    }

    public void updateTranscation(Transaction t){
        if (transactionRepository.existsById(t.getId())) {
            transactionRepository.save(t);
        } else{
            //TODO: Errorhandling in case the Transaction isn't in the database
        }
    }

    private Transaction setTransaction(Transaction temp, Inquiry inquiry, Transaction.Status status, LocalDateTime returnDate, LocalDateTime timestamp) {
        temp.setInquiry(inquiry);
        temp.setStatus(status);
        temp.setReturnDate(returnDate);
        temp.setTimestamp(timestamp);
        temp.setUpdated(LocalDateTime.now());
        return temp;
    }
}
