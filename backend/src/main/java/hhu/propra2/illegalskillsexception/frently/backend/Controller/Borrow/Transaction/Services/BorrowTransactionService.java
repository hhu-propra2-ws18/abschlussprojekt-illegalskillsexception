package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.IServices.IBorrowTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BorrowTransactionService implements IBorrowTransactionService {

    ITransactionRepository transactionRepository;
    IApplicationUserService userService;

    @Override
    public List<Transaction> retrieveAllOfCurrentUser(Authentication auth) {
        ApplicationUser currentUser = userService.getCurrentUser(auth);
        return transactionRepository.findAllByInquiry_Borrower_Id(currentUser.getId());
    }
}
