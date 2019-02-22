package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.DTOs.LendArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.Services.LendArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lend/article")
@AllArgsConstructor
public class LendArticleController {

    private final LendArticleService lendArticleService;
    private final ApplicationUserService userService;


    @PostMapping("/create")
    public FrentlyResponse createLendArticle(Authentication authentication, @RequestBody Article article) {

        ApplicationUser user = userService.getCurrentUser(authentication);
        FrentlyResponse response = new FrentlyResponse();

        try {
            final Article changedArticle = lendArticleService.createArticle(article, user);
            response.setData(changedArticle);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError(e);
            response.setError(error);
        }
        return response;
    }

    @GetMapping("/")
    public FrentlyResponse retrieveLendArticleList(Authentication authentication) {
        ApplicationUser user = userService.getCurrentUser(authentication);
        FrentlyResponse response = new FrentlyResponse();

        try {
            final List<Article> articleList = lendArticleService.retrieveArticleList(user);
            response.setData(articleList);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError(e);
            response.setError(error);
        }
        return response;
    }

    @PostMapping("/update")
    public FrentlyResponse updateLendArticleList(@RequestBody LendArticleUpdate lendArticle) {
        FrentlyResponse response = new FrentlyResponse();

        try {
            final Article article = lendArticleService.updateArticle(lendArticle);
            response.setData(article);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError(e);
            response.setError(error);
        }

        return response;
    }

}
