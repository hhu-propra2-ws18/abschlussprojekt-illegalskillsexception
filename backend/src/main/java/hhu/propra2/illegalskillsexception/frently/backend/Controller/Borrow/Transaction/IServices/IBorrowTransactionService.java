package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.DTOs.ReturnItemRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.NoSuchTransactionException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IBorrowTransactionService {
    List<Transaction> retrieveAllOfCurrentUser(Authentication auth) throws Exception;

    Transaction updateTransaction(ReturnItemRequestDTO update) throws NoSuchTransactionException, InsuffientFundsException, ProPayConnectionException, UserNotFoundException;
}
