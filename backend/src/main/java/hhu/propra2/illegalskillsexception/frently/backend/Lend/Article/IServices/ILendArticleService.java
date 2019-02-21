package hhu.propra2.illegalskillsexception.frently.backend.Lend.Article.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Lend.Article.DTO.LendArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;

import java.util.List;

public interface ILendArticleService {
    Article createArticle(Article article, ApplicationUser user);

    List<Article> retrieveArticleList(ApplicationUser owner);

    Article updateArticle(LendArticleUpdate lendArticle) throws Exception;
}
