package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Service;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.LendTransactionUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.NoSuchTransactionException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.IService.ILendTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class LendTransactionService implements ILendTransactionService {

    private final ITransactionRepository transactionRepository;
    private final IProPayService proPayService;
    private final IApplicationUserService userService;

    public Transaction createTransaction(Inquiry inquiry) {
        Transaction transaction = new Transaction();
        transaction.setInquiry(inquiry);
        transaction.setStatus(Transaction.Status.open);
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Authentication auth, LendTransactionUpdate update) throws NoSuchTransactionException {
        ApplicationUser currentUser = userService.getCurrentUser(auth);
        Transaction temp = transactionRepository.findById(update.getTransactionId()).orElseThrow(NoSuchTransactionException::new);

        if(couldWithdrawRent(temp, currentUser.getUsername())){
            temp.setStatus(update.isFaulty() ? Transaction.Status.conflict : Transaction.Status.closed);
        } else {
            temp.setStatus(Transaction.Status.MONEY_CONFLICT);
        }

        temp.setReturnDate(LocalDate.now());
        transactionRepository.save(temp);
        return temp;

    }

    private boolean couldWithdrawRent(Transaction transaction, String lender) {
        double amount = calculateFee(transaction.getInquiry().getStartDate(),
                transaction.getInquiry().getArticle().getDailyRate());

        String borrower = transaction.getInquiry().getBorrower().getUsername();

        if (proPayService.hasEnoughMoney(borrower, amount)) {
            proPayService.transferMoney(borrower, lender, amount);
            return true;
        } else {
            return false;
        }
    }

    private double calculateFee(LocalDate start, Double dailyRate) {
        return (start.until(LocalDate.now()).getDays() + 1) * dailyRate;
    }
}
