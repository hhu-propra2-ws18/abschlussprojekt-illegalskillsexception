package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Transaction.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IBorrowTransactionService {
    List<Transaction> retrieveAllOfCurrentUser(Authentication auth);
}
