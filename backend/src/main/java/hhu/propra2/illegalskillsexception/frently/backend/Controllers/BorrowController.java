/*package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.LendingPeriod;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ArticleService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private ArticleService articleService;

    @Autowired
    public BorrowController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/getAll")
    public FrentlyResponse getAllArticles(Authentication authentication) {
        FrentlyResponse frResponse = new FrentlyResponse();
        try {
            List<Article> dataList = articleService.getAllArticles();
            frResponse.setData(dataList);
        }
        catch (Exception e) {
            FrentlyError error = new FrentlyError();
            error.setErrorMessage("Could not get Articles");
            error.setErrorCode(1);
            frResponse.setError(error);
        }
        return frResponse;
    }

    @PostMapping("/inquiry")
    public FrentlyResponse createInquiry(Authentication authentication, @RequestBody Inquiry inquiry) {
        FrentlyResponse frentlyResponse = new FrentlyResponse();
        try {
            
        }
    }

}
*/