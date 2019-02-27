package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.DTOs.LendArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.Exceptions.PendingInquiryException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Article;

import java.util.List;

public interface ILendArticleService {
    Article createArticle(Article article, ApplicationUser user);

    List<Article> retrieveArticleList(ApplicationUser owner);

    Article updateArticle(LendArticleUpdate lendArticle) throws NoSuchArticleException, PendingInquiryException;
}
