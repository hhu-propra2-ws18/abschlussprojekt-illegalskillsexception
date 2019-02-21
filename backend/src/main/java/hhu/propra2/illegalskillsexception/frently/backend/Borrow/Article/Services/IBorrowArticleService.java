package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Article.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;

import java.util.List;

public interface IBorrowArticleService {
    List<Article> retrieveAll();

    List<Article> retrieveAllOfOwner(long ownerId);

    Article getArticleById(long articleId) throws NoSuchArticleException;
}
