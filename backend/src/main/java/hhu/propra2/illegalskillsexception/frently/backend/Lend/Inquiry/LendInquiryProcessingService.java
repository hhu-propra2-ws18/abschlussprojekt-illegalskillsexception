package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IInquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LendInquiryProcessingService {

    private final IInquiryRepository inquiryRepository;

    Inquiry declineInquiry(Long inquiryId) throws Exception {

        Optional<Inquiry> inquiryOpt = inquiryRepository.findById(inquiryId);

        if(!inquiryOpt.isPresent()) {
            throw new Exception();
        }

        final Inquiry inquiry = inquiryOpt.get();
        inquiry.setStatus(Inquiry.Status.declined);

        return inquiry;
    }
}
