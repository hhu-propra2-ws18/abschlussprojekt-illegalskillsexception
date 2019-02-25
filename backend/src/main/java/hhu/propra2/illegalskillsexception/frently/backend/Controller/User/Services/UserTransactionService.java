package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserTransactionService implements IUserTransactionService {

    private final ITransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllFinishedTransactions(ApplicationUser user) {

        List<Transaction> allTransactions = transactionRepository.findAll();
        List<Transaction> filteredRelatedToUserAndClosed = new ArrayList<>();
        for(Transaction transaction : allTransactions){

            //Check if CLOSED
            if (transaction.getStatus() != Transaction.Status.CLOSED) {
                //Not CLOSED, ignore transaction
                continue;
            }
            //Check if current user is the lender
            if (transaction.getInquiry().getLender().getId() == user.getId()) {
                filteredRelatedToUserAndClosed.add(transaction);
                //Break, since items don't need to be added twice
                continue;
            }

            //Check if current user is borrower
            if (transaction.getInquiry().getBorrower().getId() == user.getId()) {
                filteredRelatedToUserAndClosed.add(transaction);
            }
        }
        return filteredRelatedToUserAndClosed;
    }

    public List<Transaction> allOverdueTransactions(long userId) {
        return transactionRepository.findAllByInquiry_Borrower_IdAndInquiry_EndDateLessThan(userId, LocalDate.now());
    }
}
