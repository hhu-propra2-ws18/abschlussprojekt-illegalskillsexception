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

    private final IApplicationUserService userService;
    private final IArticleService articleService;
    private final IInquiryRepository inquiries;

    @Override
    public long createInquiry(Authentication auth, BorrowInquiryDTO dto) throws ArticleNotAvailableException {
        ApplicationUser currentUser = userService.getCurrentUser(auth);

        Inquiry inquiry = new Inquiry();

        Article article = articleService.getArticleById(dto.getArticleId());
        inquiry.setArticle(article);

        inquiry.setBorrower(currentUser);
        inquiry.setLender(article.getOwner());

        // get all inquiries involving this article and check if the lending periods intersect

        //TreeSet<Inquiry> allInquiriesInvolvingArticle = inquiries.findAllByArticle_IdOrderByStartDate(article.getId());
        //allInquiriesInvolvingArticle.

        inquiry.setStatus(Inquiry.Status.OPEN);
        inquiry.setStartDate(dto.getStartDate());
        inquiry.setEndDate(dto.getEndDate());

        inquiries.save(inquiry);
        return inquiry.getId();
    }

    @Override
    public List<Inquiry> retrieveAllInquiriesByUser(Authentication auth) {
        return null;
    }
}
