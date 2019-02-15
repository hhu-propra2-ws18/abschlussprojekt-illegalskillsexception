package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private IArticleService articleService;

    @GetMapping("/all")
    public List<Article> showAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article showDetails(@PathVariable Long id) {
        return articleService.getArticleById(id);
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
