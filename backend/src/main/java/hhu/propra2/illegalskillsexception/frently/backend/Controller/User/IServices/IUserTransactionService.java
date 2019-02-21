package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;

import java.util.List;

public interface IUserTransactionService {

    List<Transaction> getAllFinishedTransactions(ApplicationUser user);

}
