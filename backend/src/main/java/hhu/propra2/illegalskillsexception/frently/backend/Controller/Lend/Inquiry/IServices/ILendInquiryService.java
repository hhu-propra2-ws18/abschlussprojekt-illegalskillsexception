package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;

import java.util.List;

public interface ILendInquiryService {
    List<Inquiry> retrieveInquiriesFromUser(ApplicationUser user);
}
