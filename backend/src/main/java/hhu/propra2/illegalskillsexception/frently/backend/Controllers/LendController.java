package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyData;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lend")
public class LendController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getall")
    public FrentlyResponse getAllArticlesOwner(Authentication authentication) {
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        List<Article> articles = articleService.getAllArticlesOfOwner(user);
        List<FrentlyData> dataList = new ArrayList<>();
        dataList.addAll(articles);
        return new FrentlyResponse(null,dataList);
    }

    @PostMapping("/item")
    public FrentlyResponse createNewItem(Authentication authentication, @RequestBody Article article) {
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        articleService.createArticle(user,article);
        List<FrentlyData> dataList = new ArrayList<>();
        dataList.add(article);
        return new FrentlyResponse(null,dataList);
    }
}
