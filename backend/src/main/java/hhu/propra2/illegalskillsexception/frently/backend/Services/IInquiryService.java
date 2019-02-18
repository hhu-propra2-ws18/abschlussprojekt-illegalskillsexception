package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.LendingPeriod;
import java.util.List;

public interface IInquiryService {
    long createInquiry(Article article, ApplicationUser borrower, LendingPeriod lendingPeriod, Inquiry.Status status);
    Inquiry updateInquiry(Inquiry inquiry);
    Inquiry getInquiry(long id);
    List<Inquiry> getAllInquirys();
}
