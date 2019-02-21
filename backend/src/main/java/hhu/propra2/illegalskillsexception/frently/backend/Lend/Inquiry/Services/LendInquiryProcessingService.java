package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
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
public class LendInquiryProcessingService {

    private IInquiryRepository inquiryRepository;
    private ITransactionRepository transactionRepository;

    public Inquiry declineInquiry(Long inquiryId) throws Exception {

        Optional<Inquiry> inquiryOpt = inquiryRepository.findById(inquiryId);
        final Inquiry inquiry = inquiryOpt.orElseThrow(Exception::new);

        inquiry.setStatus(Inquiry.Status.DECLINED);
        return inquiry;
    }

    Transaction processAcceptInquiry(Long inquiryId) throws Exception {
        // TODO Add ProPay
        // Check, if lender has enough money, block deposit and transfer fee.
        long reservationId = 3; //Todo payInMoney()

        Inquiry inquiry = acceptInquiry(inquiryId);

        return createTransactionFromInquiry(inquiry, reservationId);
    }

    Double calculateFee(LocalDate start, LocalDate end, Double dailyRate) {
        return (start.until(end).getDays() + 1) * dailyRate;
    }

    private Inquiry acceptInquiry(Long inquiryId) throws Exception {
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
}
