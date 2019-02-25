package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Article;

import java.util.List;

public interface IBorrowArticleService {
    List<Article> retrieveAll();

    List<Article> retrieveAllOfOwner(long ownerId);

    Article getArticleById(long articleId) throws NoSuchArticleException;
}
