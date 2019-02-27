package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices.IProcessConflictService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction.Status.CLOSED;

@Service
@AllArgsConstructor
public class ProcessConflictService implements IProcessConflictService {

    private final ITransactionRepository transactionRepository;
    private final IProPayService proPayService;

    @Override
    public Transaction releaseConflictingTransaction(long transactionId) throws Exception {
        Transaction transaction = processConflict(transactionId);
        proPayService.freeDeposit(getBorrower(transaction), transaction);
        return transaction;
    }

    @Override
    public Transaction punishConflictingTransaction(long transactionId) throws Exception {
        Transaction transaction = processConflict(transactionId);
        proPayService.punishUser(getBorrower(transaction), transaction);
        return transaction;
    }

    private Transaction processConflict(long transactionId) throws Exception {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(Exception::new);
        transaction.setStatus(CLOSED);
        return transactionRepository.save(transaction);
    }

    private String getBorrower(Transaction transaction) {
        return transaction.getInquiry().getBorrower().getUsername();
    }
}
