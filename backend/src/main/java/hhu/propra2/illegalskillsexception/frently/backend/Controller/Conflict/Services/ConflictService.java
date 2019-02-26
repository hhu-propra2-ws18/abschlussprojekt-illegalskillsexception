package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConflictService {

    private final ITransactionRepository transactionRepository;

    public List<Transaction> retrieveAllConflictedTransacions() {
        return transactionRepository.findAll();
    }

}
