package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.Exceptions.LendBorrowerHasNotEnoughMoneyException;
import hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.IServices.ILendInquiryProcessingService;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IInquiryRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.ITransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class LendInquiryProcessingService implements ILendInquiryProcessingService {

    private IInquiryRepository inquiryRepository;
    private ITransactionRepository transactionRepository;
    private IProPayService proPayService;

    @Override
    public Inquiry declineInquiry(Long inquiryId) throws Exception {

        Optional<Inquiry> inquiryOpt = inquiryRepository.findById(inquiryId);
        final Inquiry inquiry = inquiryOpt.orElseThrow(Exception::new);

        inquiry.setStatus(Inquiry.Status.DECLINED);
        return inquiry;
    }

    @Override
    public Transaction acceptInquiry(Long inquiryId) throws Exception {

        Inquiry inquiry = processAcceptedInquiry(inquiryId);
        Article article = inquiry.getArticle();

        double fee = calculateFee(inquiry.getStartDate(), inquiry.getEndDate(), article.getDailyRate());
        boolean hasEnoughMoney = proPayService.hasEnoughMoney(
                inquiry.getLender().getUsername(), fee+article.getDeposit());

        if(!hasEnoughMoney) {
            throw new LendBorrowerHasNotEnoughMoneyException();
        }

        long reservationId = processPayment(
                inquiry.getBorrower(),
                article.getOwner(),
                article.getDeposit(),
                fee
        );

        return createTransactionFromInquiry(inquiry, reservationId);
    }

    double calculateFee(LocalDate start, LocalDate end, Double dailyRate) {
        return (start.until(end).getDays() + 1) * dailyRate;
    }

    private Inquiry processAcceptedInquiry(Long inquiryId) throws Exception {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(Exception::new);
        inquiry.setStatus(Inquiry.Status.ACCEPTED);
        return inquiryRepository.save(inquiry);
    }

    private Transaction createTransactionFromInquiry(Inquiry inquiry, long reservationId) {
        Transaction transaction = new Transaction();
        transaction.setInquiry(inquiry);
        transaction.setStatus(Transaction.Status.open);
        transaction.setReservationId(reservationId);
        return transactionRepository.save(transaction);
    }

    private Long processPayment(ApplicationUser borrower, ApplicationUser lender, double deposit, double fee)
            throws Exception {
        String borrowerName = borrower.getUsername();
        String lenderName = lender.getUsername();

        Long reservationId = proPayService.blockDeposit(borrowerName, lenderName, deposit);
        proPayService.transferMoney(borrowerName, lenderName, fee);

        return reservationId;
    }
}
