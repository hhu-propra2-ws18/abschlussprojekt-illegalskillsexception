package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Transaction.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Borrow.Transaction.IServices.IBorrowTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.ITransactionRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IApplicationUserService;
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
