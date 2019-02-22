package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.IService;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.LendTransactionUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.NoSuchTransactionException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import org.springframework.security.core.Authentication;

public interface ILendTransactionService {
    Transaction updateTransaction(Authentication auth, LendTransactionUpdate update)
            throws NoSuchTransactionException;

    Transaction createTransaction(Inquiry inquiry);
}
