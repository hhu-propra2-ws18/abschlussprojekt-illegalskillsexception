package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.DTOs.BorrowInquiryDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Exceptions.ArticleNotAvailableException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Exceptions.InvalidLendingPeriodException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IBorrowInquiryService {
    Inquiry createInquiry(Authentication auth, BorrowInquiryDTO inquiryDTO)
            throws ArticleNotAvailableException, NoSuchArticleException, InvalidLendingPeriodException;

    List<Inquiry> retrieveAllInquiriesByUser(Authentication auth);
}
