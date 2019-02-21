package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Borrow.Article.Services.IBorrowArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Borrow.Inquiry.DTOs.BorrowInquiryDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.ArticleNotAvailableException;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.InvalidLendingPeriodException;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IInquiryRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BorrowInquiryService implements IBorrowInquiryService {

    private final IInquiryRepository inquiries;

    private final IApplicationUserService userService;
    private final IBorrowArticleService articleService;

    @Override
    public Inquiry createInquiry(Authentication auth, BorrowInquiryDTO dto)
            throws ArticleNotAvailableException, InvalidLendingPeriodException, NoSuchArticleException {
        ApplicationUser currentUser = userService.getCurrentUser(auth);

        if (isInvalidPeriod(dto)) throw new InvalidLendingPeriodException();
        if (hasDateConflict(dto)) throw new ArticleNotAvailableException();

        Inquiry inquiry = new Inquiry();

        Article article = articleService.getArticleById(dto.getArticleId());

        inquiry.setArticle(article);
        inquiry.setBorrower(currentUser);
        inquiry.setLender(article.getOwner());
        inquiry.setStatus(Inquiry.Status.OPEN);
        inquiry.setStartDate(dto.getStartDate());
        inquiry.setEndDate(dto.getEndDate());


        inquiries.save(inquiry);
        return inquiry;
    }

    @Override
    public List<Inquiry> retrieveAllInquiriesByUser(Authentication auth) {
        ApplicationUser currentUser = userService.getCurrentUser(auth);
        return inquiries.findAllByBorrower_Id(currentUser.getId());
    }

    private boolean hasDateConflict(BorrowInquiryDTO dto) {
        List<Inquiry> allConflictingInquiries =
                inquiries.findAllByArticle_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        dto.getArticleId(), dto.getEndDate(), dto.getStartDate());
        return !allConflictingInquiries.isEmpty();
    }

    private boolean isInvalidPeriod(BorrowInquiryDTO dto) {
        return dto.getEndDate().isBefore(dto.getStartDate());
    }
}
