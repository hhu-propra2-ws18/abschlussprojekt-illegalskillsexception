package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IInquiryRepository;
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

    Inquiry declineInquiry(Long inquiryId) throws Exception {

        Optional<Inquiry> inquiryOpt = inquiryRepository.findById(inquiryId);

        if(!inquiryOpt.isPresent()) {
            throw new Exception();
        }

        final Inquiry inquiry = inquiryOpt.get();
        inquiry.setStatus(Inquiry.Status.declined);

        return inquiry;
    }
/*
    Inquiry acceptInquiry(Long inquiryId) {
        // TODO Add ProPay
        // Check, if lender has enough money, block deposit and transfer fee.

    }
*/
    Double calculateFee(LocalDate start, LocalDate end, Double dailyRate) {
        return (start.until(end).getDays() + 1) * dailyRate;
    }
}
