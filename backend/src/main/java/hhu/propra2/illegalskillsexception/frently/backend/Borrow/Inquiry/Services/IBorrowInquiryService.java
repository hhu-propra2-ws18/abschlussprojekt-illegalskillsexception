package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Borrow.Inquiry.DTOs.BorrowInquiryDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.ArticleNotAvailableException;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.InvalidLendingPeriodException;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IBorrowInquiryService {
    Inquiry createInquiry(Authentication auth, BorrowInquiryDTO inquiryDTO)
            throws ArticleNotAvailableException, NoSuchArticleException, InvalidLendingPeriodException;

    List<Inquiry> retrieveAllInquiriesByUser(Authentication auth);
}
