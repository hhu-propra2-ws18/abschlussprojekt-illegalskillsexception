package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices.IConflictService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConflictService implements IConflictService {

    private final ITransactionRepository transactionRepository;

    @Override
    public List<Transaction> retrieveAllConflictingTransactions() {
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.
                        getStatus() == Transaction.Status.CONFLICT)
                .collect(Collectors.toList());
    }

}
