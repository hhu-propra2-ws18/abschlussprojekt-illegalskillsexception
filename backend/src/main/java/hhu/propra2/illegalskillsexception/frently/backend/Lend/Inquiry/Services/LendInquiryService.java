package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.IServices.ILendInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IInquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LendInquiryService implements ILendInquiryService {

    private final IInquiryRepository IInquiryRepository;

    @Override
    public List<Inquiry> retrieveInquiriesFromUser(ApplicationUser user) {
        return IInquiryRepository.findAllByLender_Id(user.getId());
    }

}
