package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public FrentlyResponse getAllArticles(Authentication authentication){
        FrentlyResponse response = new FrentlyResponse();
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        try{
            List<Article> articleList= articleService.getAllArticles();
            response.setData(articleList);
        } catch(Exception e){
            FrentlyError error = new FrentlyError("Could not find any articles.", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }
}