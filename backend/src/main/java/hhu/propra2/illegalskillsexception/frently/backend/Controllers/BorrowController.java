package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.LendingPeriod;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private ArticleService articleService;
    private InquiryService inquiryService;
    private ApplicationUserService userService;

    @Autowired
    public BorrowController(ArticleService articleService, InquiryService inquiryService, ApplicationUserService userService) {
        this.articleService = articleService;
        this.inquiryService = inquiryService;
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public FrentlyResponse getAllArticles(Authentication authentication) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            List<Article> articleList = articleService.getAllArticles();
            response.setData(articleList);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError("Could not find any articles.", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }

    @PostMapping("/inquiry")
    public FrentlyResponse makeInquiry(Authentication authentication, @RequestParam long id,
                                       @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        FrentlyResponse response = new FrentlyResponse();
        ApplicationUser borrower = userService.getCurrentUser(authentication);
        try {
            Article inquiryArticle = articleService.getArticleById(id);
            LendingPeriod period = new LendingPeriod(startDate, endDate);
            long inquiryId = inquiryService.createInquiry(inquiryArticle, borrower, period, Inquiry.Status.open);
            response.setData(inquiryId);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError("Could not creat new inquiry.", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }
}