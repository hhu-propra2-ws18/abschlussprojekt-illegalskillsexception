package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lend")
@AllArgsConstructor
public class LendController {

    private final ApplicationUserService userService;
    private final ArticleService articleService;

    @GetMapping("/getAll")
    public FrentlyResponse getAllArticlesOfOwner(Authentication authentication) {
        FrentlyResponse response = new FrentlyResponse();
        ApplicationUser currentUser = userService.getCurrentUser(authentication);
        try {
            List<Article> articleList = articleService.getAllArticlesOfOwner(currentUser);
            response.setData(articleList);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError("Could not get articles of owner", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }

    @PostMapping("/create")
    public FrentlyResponse createOffer(Authentication authentication, @RequestBody Article article) {
        FrentlyResponse response = new FrentlyResponse();

        ApplicationUser currentUser = userService.getCurrentUser(authentication);
        try {
            articleService.createArticle(currentUser, article);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError("Failed to create offer", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }
}