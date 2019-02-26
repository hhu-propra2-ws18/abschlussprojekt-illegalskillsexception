package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices.ILendInquiryProcessingService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchInquiryException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.ITransactionRepository;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LendInquiryProcessingService implements ILendInquiryProcessingService {

    private IInquiryRepository inquiryRepository;
    private ITransactionRepository transactionRepository;
    private IProPayService proPayService;

    @Override
    public Inquiry declineInquiry(Long inquiryId) throws NoSuchInquiryException {

        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(NoSuchInquiryException::new);

        flagAsDeclined(inquiry);
        return inquiry;
    }

    @Override
    public Transaction acceptInquiry(Long inquiryId)
            throws NoSuchInquiryException, ProPayConnectionException, InsuffientFundsException {

        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(NoSuchInquiryException::new);
        Article article = inquiry.getArticle();

        ApplicationUser borrower = inquiry.getBorrower();
        ApplicationUser lender = article.getOwner();
        Double deposit = article.getDeposit();

        long reservationId = blockDeposit(borrower, lender, deposit);

        flagAsAccepted(inquiry);
        return createTransactionFromInquiry(inquiry, reservationId);
    }

    private Transaction createTransactionFromInquiry(Inquiry inquiry, long reservationId) {
        Transaction transaction = new Transaction();
        transaction.setInquiry(inquiry);
        transaction.setStatus(Transaction.Status.OPEN);
        transaction.setReservationId(reservationId);
        return transactionRepository.save(transaction);
    }

    private Long blockDeposit(ApplicationUser borrower, ApplicationUser lender, double deposit)
            throws ProPayConnectionException, InsuffientFundsException {
        String borrowerName = borrower.getUsername();
        String lenderName = lender.getUsername();

        Long reservationId = proPayService.blockDeposit(borrowerName, lenderName, deposit);
        return reservationId;
    }

    private void flagAsAccepted(Inquiry inquiry) {
        inquiry.setStatus(Inquiry.Status.ACCEPTED);
        inquiryRepository.save(inquiry);
    }

    private void flagAsDeclined(Inquiry inquiry) {
        inquiry.setStatus(Inquiry.Status.DECLINED);
        inquiryRepository.save(inquiry);
    }
}
