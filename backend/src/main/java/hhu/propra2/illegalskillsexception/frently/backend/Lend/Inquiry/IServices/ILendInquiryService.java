package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;

import java.util.List;

public interface ILendInquiryService {
    List<Inquiry> retrieveInquiriesFromUser(ApplicationUser user);
}
