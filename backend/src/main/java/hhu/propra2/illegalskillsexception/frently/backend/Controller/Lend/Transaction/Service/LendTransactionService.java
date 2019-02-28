package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Service;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.AcceptReturnedItemRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.ArticleNotReturnedException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.NoSuchTransactionException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.IService.ILendTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LendTransactionService implements ILendTransactionService {

    private final ITransactionRepository transactionRepository;
    private final IApplicationUserService userService;

    @Override
    public Transaction createTransaction(Inquiry inquiry) {
        Transaction transaction = new Transaction();
        transaction.setInquiry(inquiry);
        transaction.setStatus(Transaction.Status.OPEN);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> retrieveAllOfCurrentUser(Authentication auth) {
        ApplicationUser currentUser = userService.getCurrentUser(auth);
        return transactionRepository.findAllByInquiry_Lender_Id(currentUser.getId());
    }

    @Override
    public Transaction updateTransaction(AcceptReturnedItemRequestDTO dto) throws ArticleNotReturnedException, NoSuchTransactionException {
        Transaction transaction = transactionRepository.findById(dto.getTransactionId())
                .orElseThrow(NoSuchTransactionException::new);

        if (transaction.getStatus() != Transaction.Status.RETURNED) {
            throw new ArticleNotReturnedException();
        }

        if (dto.isFaulty()) {
            transaction.setStatus(Transaction.Status.CONFLICT);
        } else {
            transaction.setStatus(Transaction.Status.CLOSED);
        }
        transactionRepository.save(transaction);
        return transaction;

    }
}
