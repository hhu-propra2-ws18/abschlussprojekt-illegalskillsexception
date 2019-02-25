package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Service;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.TransactionUpdateRequestDTO;
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
import java.util.List;

@Service
@AllArgsConstructor
public class LendTransactionService implements ILendTransactionService {

    private final ITransactionRepository transactionRepository;
    private final IProPayService proPayService;
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
    public Transaction updateTransaction(TransactionUpdateRequestDTO update) throws NoSuchTransactionException {
        Transaction transaction = transactionRepository.findById(update.getTransactionId()).orElseThrow(NoSuchTransactionException::new);

        if (couldWithdrawRent(transaction)) {
            transaction.setStatus(update.isFaulty() ? Transaction.Status.CONFLICT : Transaction.Status.CLOSED);
        } else {
            transaction.setStatus(Transaction.Status.PENDING_PAYMENT);
        }

        transaction.setReturnDate(LocalDate.now());
        transactionRepository.save(transaction);
        return transaction;

    }

    private boolean couldWithdrawRent(Transaction transaction) {
        LocalDate startDate = transaction.getInquiry().getStartDate();
        Double dailyRate = transaction.getInquiry().getArticle().getDailyRate();
        double fee = calculateFee(startDate, dailyRate);

        String borrower = transaction.getInquiry().getBorrower().getUsername();
        String lender = transaction.getInquiry().getLender().getUsername();

        if (proPayService.hasEnoughMoney(borrower, fee)) {
            proPayService.transferMoney(borrower, lender, fee);
            return true;
        } else {
            return false;
        }
    }

    private double calculateFee(LocalDate startDate, Double dailyRate) {
        return (startDate.until(LocalDate.now()).getDays() + 1) * dailyRate;
    }
}
