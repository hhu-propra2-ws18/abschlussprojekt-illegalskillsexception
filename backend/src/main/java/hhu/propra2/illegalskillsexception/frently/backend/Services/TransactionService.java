package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository pTransactionRepo) {
        this.transactionRepository = pTransactionRepo;
    }

    public void createTransaction(Transaction.State state, Inquiry inquiry, LocalDateTime returnDate){
        final Transaction temp = setTransaction(new Transaction(), inquiry,Transaction.State.open, returnDate);
        transactionRepository.save(temp);
    }

    private Transaction setTransaction(Transaction temp, Inquiry inquiry, Transaction.State state, LocalDateTime returnDate) {
        temp.setInquiry(inquiry);
        temp.setState(state);
        temp.setReturnDate(returnDate);
        temp.setTimestamp(LocalDateTime.now());
        temp.setUpdated(LocalDateTime.now());
        return temp;
    }
}
