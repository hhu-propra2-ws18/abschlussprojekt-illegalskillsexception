package hhu.propra2.illegalskillsexception.frently.backend.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;

import java.util.List;

public interface IUserTransactionService {

    List<Transaction> getAllFinishedTransactions(ApplicationUser user);

}
