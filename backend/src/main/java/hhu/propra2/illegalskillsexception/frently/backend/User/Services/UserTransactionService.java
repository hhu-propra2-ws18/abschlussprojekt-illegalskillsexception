package hhu.propra2.illegalskillsexception.frently.backend.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

            //Check if closed
            if(transaction.getStatus() != Transaction.Status.closed){
                //Not closed, ignore transaction
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
}
