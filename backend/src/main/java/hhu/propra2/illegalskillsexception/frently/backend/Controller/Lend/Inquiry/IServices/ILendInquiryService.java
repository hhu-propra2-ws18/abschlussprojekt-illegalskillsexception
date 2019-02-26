package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.DTOs.LendInquiryResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;

import java.util.List;

public interface ILendInquiryService {
    List<LendInquiryResponseDTO> retrieveInquiriesFromUser(ApplicationUser user);
}
