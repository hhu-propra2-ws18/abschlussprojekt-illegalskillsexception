package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Article.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;

import java.util.List;

public interface IBorrowArticleService {
    List<Article> retrieveAll();

    List<Article> retrieveAllOfOwner(long ownerId);
}
