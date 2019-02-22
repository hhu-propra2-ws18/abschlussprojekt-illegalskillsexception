package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MockUserTransactionService implements IUserTransactionService {
    @Override
    public List<Transaction> getAllFinishedTransactions(ApplicationUser user) {
        ArrayList<Transaction> list = new ArrayList<>(1);
        list.add(new Transaction());
        return list;
    }

    @Override
    public List<Transaction> allOverdueTransactions(long userId) {
        return null;
    }
}
