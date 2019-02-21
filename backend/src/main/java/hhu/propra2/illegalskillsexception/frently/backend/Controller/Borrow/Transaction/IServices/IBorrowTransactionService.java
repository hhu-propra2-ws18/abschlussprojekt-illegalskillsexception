package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IBorrowTransactionService {
    List<Transaction> retrieveAllOfCurrentUser(Authentication auth);
}
