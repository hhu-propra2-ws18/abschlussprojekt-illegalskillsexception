package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.DTOs.LendArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;

import java.util.List;

public interface ILendArticleService {
    BorrowArticle createArticle(BorrowArticle borrowArticle, ApplicationUser user);

    List<BorrowArticle> retrieveArticleList(ApplicationUser owner);

    BorrowArticle updateArticle(LendArticleUpdate lendArticle) throws NoSuchArticleException;
}
