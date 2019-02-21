package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Borrow.Inquiry.DTOs.BorrowInquiryDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.ArticleNotAvailableException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IInquiryRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

@Service
@AllArgsConstructor
public class BorrowInquiryService implements IBorrowInquiryService {

    private final IInquiryRepository inquiries;

    private final IApplicationUserService userService;
    private final IArticleService articleService;

    @Override
    public long createInquiry(Authentication auth, BorrowInquiryDTO dto) throws ArticleNotAvailableException {
        ApplicationUser currentUser = userService.getCurrentUser(auth);

        Inquiry inquiry = new Inquiry();

        Article article = articleService.getArticleById(dto.getArticleId());


        inquiry.setArticle(article);
        inquiry.setBorrower(currentUser);
        inquiry.setLender(article.getOwner());
        inquiry.setStatus(Inquiry.Status.OPEN);
        inquiry.setStartDate(dto.getStartDate());
        inquiry.setEndDate(dto.getEndDate());

        if (hasDateConflict(dto)) throw new ArticleNotAvailableException();

        inquiries.save(inquiry);
        return inquiry.getId();
    }

    @Override
    public List<Inquiry> retrieveAllInquiriesByUser(Authentication auth) {
        return null;
    }

    private boolean hasDateConflict(BorrowInquiryDTO dto) {
        List<Inquiry> allConflictingInquiries = inquiries.findAllByArticle_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(dto.getArticleId(), dto.getStartDate(), dto.getEndDate());
        return !allConflictingInquiries.isEmpty();
    }
}
