package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyData;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private IArticleService articleService;

    @GetMapping("/all")
    public FrentlyResponse showAllArticles() {
        List<Article> dataList = articleService.getAllArticles();
        return new FrentlyResponse(null,new ArrayList<>(dataList));
    }

    @GetMapping("/{id}")
    public FrentlyResponse showDetails(@PathVariable Long id) {
        FrentlyData article = articleService.getArticleById(id);
        List<FrentlyData> data = new ArrayList<>();
        data.add(article);
        return new FrentlyResponse(null,data);
    }

    @PostMapping("/create")
    public void createArticle(@RequestBody ApplicationUser owner, @RequestBody Article article) {
        articleService.createArticle(owner, article);
    }

    @PutMapping("/update")
    public void updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }


}
