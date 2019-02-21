package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;

import java.util.List;

public interface IArticleService {

    void createArticle(ApplicationUser owner, Article article);

    List<Article> getAllArticles();

    Article getArticleById(Long id);

    void updateArticle(Article article);

    void deleteArticle(Long id);

}
