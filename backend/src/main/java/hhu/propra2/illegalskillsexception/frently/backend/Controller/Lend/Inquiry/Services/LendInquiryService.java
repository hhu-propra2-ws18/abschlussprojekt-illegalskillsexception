package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.DTOs.LendInquiryResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices.ILendInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LendInquiryService implements ILendInquiryService {

    private final IInquiryRepository inquiryRepository;

    @Override
    public List<LendInquiryResponseDTO> retrieveInquiriesFromUser(ApplicationUser user) {
        List<Inquiry> inquiryList = inquiryRepository.findAllByLender_Id(user.getId());
        List<Inquiry> validInquiries = getInquiries(inquiryList);

        List<LendInquiryResponseDTO> responseDTOs = new ArrayList<>();
        for (Inquiry inquiry : validInquiries) {
            LendInquiryResponseDTO dto = new LendInquiryResponseDTO(inquiry);
            responseDTOs.add(dto);
        }
        return responseDTOs;
    }

    List<Inquiry> getOpenInquiries(List<Inquiry> inquiryList) {
        List<Inquiry> openInquiries = new ArrayList<>();

        for (Inquiry inquiry : inquiryList) {
            Inquiry.Status status = inquiry.getStatus();
            if (status == Inquiry.Status.OPEN) {
                openInquiries.add(inquiry);
            }
        }

        return openInquiries;
    }

    private List<Inquiry> getInquiries(List<Inquiry> inquiries) {
        return removeExpiredInquires(getOpenInquiries(inquiries), LocalDate.now());
    }

    List<Inquiry> removeExpiredInquires(List<Inquiry> inquiries, LocalDate localDate) {
        List<Inquiry> validInquiries = new ArrayList<>();
        for (Inquiry inquiry : inquiries) {
            if (inquiry.getStartDate().plusDays(1L).isAfter(localDate)) {
                validInquiries.add(inquiry);
            } else {
                inquiry.setStatus(Inquiry.Status.DECLINED);
                inquiryRepository.save(inquiry);
            }
        }
        return validInquiries;
    }
}
