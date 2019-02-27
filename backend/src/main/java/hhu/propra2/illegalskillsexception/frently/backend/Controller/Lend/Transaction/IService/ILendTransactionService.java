package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.IService;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.AcceptReturnedItemRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.ArticleNotReturnedException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.NoSuchTransactionException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ILendTransactionService {
    Transaction createTransaction(Inquiry inquiry); //TODO: move method from LendInquiryProcessingService

    List<Transaction> retrieveAllOfCurrentUser(Authentication authentication);

    Transaction updateTransaction(AcceptReturnedItemRequestDTO update)
            throws ArticleNotReturnedException, NoSuchTransactionException;
}
