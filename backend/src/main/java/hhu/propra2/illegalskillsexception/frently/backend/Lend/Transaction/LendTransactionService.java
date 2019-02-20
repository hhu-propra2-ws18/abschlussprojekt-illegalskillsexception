package hhu.propra2.illegalskillsexception.frently.backend.Lend.Transaction;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LendTransactionService {

    private final ITransactionRepository transactionRepository;

    Transaction createTransaction(Inquiry inquiry) {
        Transaction transaction = new Transaction();
        transaction.setInquiry(inquiry);
        transaction.setStatus(Transaction.Status.open);
        return transactionRepository.save(transaction);
    }
}
