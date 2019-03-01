package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.DTOs.ReturnItemRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.IServices.IBorrowTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsufficientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.NoSuchTransactionException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import lombok.AllArgsConstructor;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowTransactionService implements IBorrowTransactionService {

    private final ITransactionRepository transactionRepository;
    private final IApplicationUserService userService;
    private final IProPayService proPayService;

    @Override
    public List<Transaction> retrieveAllOfCurrentUser(Authentication auth) {
        ApplicationUser currentUser = userService.getCurrentUser(auth);
        return transactionRepository.findAllByInquiry_Borrower_Id(currentUser.getId());
    }

    @Override
    public Transaction updateTransaction(ReturnItemRequestDTO update)
            throws NoSuchTransactionException, InsufficientFundsException, ProPayConnectionException, UserNotFoundException {
        Transaction transaction = transactionRepository.findById(update.getTransactionId()).orElseThrow(NoSuchTransactionException::new);
        transaction.setReturnDate(LocalDate.now());

        transferFee(transaction);

        transaction.setStatus(Transaction.Status.RETURNED);


        transactionRepository.save(transaction);
        return transaction;

    }

    private void transferFee(Transaction transaction)
            throws InsufficientFundsException, ProPayConnectionException, UserNotFoundException {
        LocalDate startDate = transaction.getInquiry().getStartDate();
        LocalDate endDate = transaction.getInquiry().getEndDate();
        Double dailyRate = transaction.getInquiry().getBorrowArticle().getDailyRate();

        double fee = calculateFee(startDate, endDate, dailyRate);

        String borrower = transaction.getInquiry().getBorrower().getUsername();
        String lender = transaction.getInquiry().getLender().getUsername();

        //Is checked here instead of inside ProPayService because transferMoney is a void
        try {
            proPayService.transferMoney(borrower, lender, fee);
        } catch (ExhaustedRetryException e){
            throw new ProPayConnectionException();
        }
    }

    double calculateFee(LocalDate startDate, LocalDate endDate, Double dailyRate) {
        return (startDate.until(endDate).getDays() + 1) * dailyRate;
    }
}
