package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.DTOs.LendInquiryResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices.ILendInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LendInquiryService implements ILendInquiryService {

    private final IInquiryRepository IInquiryRepository;

    @Override
    public List<LendInquiryResponseDTO> retrieveInquiriesFromUser(ApplicationUser user) {
        List<Inquiry> inquiryList = IInquiryRepository.findAllByLender_Id(user.getId());
        List<Inquiry> openInquiries = filterOpenInquiries(inquiryList);

        List<LendInquiryResponseDTO> responseDTOs = new ArrayList<>();
        for (Inquiry inquiry : openInquiries) {
            LendInquiryResponseDTO dto = new LendInquiryResponseDTO(inquiry);
            responseDTOs.add(dto);
        }
        return responseDTOs;
    }

    public List<Inquiry> filterOpenInquiries(List<Inquiry> inquiryList) {
        List<Inquiry> openInquiries = new ArrayList<>();

        for (Inquiry inquiry : inquiryList) {
            Inquiry.Status status = inquiry.getStatus();
            if (status == Inquiry.Status.OPEN) {
                openInquiries.add(inquiry);
            }
        }

        return openInquiries;
    }

}
