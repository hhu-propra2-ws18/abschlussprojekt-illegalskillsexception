package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lend")
public class LendController {

    private ArticleService articleService;

    @Autowired
    public LendController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/getAll")
    public FrentlyResponse getAllArticlesOwner(Authentication authentication) {
        FrentlyResponse frResponse = new FrentlyResponse();
        try {
            ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
            List<Article> articles = articleService.getAllArticlesOfOwner(user);
            frResponse.setData(articles);
        }
        catch (Exception e) {
            FrentlyError error = new FrentlyError();
            error.setErrorMessage("Could not get Articles of Owner");
            error.setErrorCode(1);
            frResponse.setError(error);
        }
        return new FrentlyResponse();
    }

    @PostMapping("/create")
    public FrentlyResponse createNewItem(Authentication authentication, @RequestBody Article article) {
        FrentlyResponse frResponse = new FrentlyResponse();
        try {
            ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
            articleService.createArticle(user, article);
        }
        catch (Exception e) {
            FrentlyError error = new FrentlyError();
            error.setErrorMessage("Could not save Article");
            error.setErrorCode(2);
            frResponse.setError(error);
        }
        return new FrentlyResponse();
    }
}
