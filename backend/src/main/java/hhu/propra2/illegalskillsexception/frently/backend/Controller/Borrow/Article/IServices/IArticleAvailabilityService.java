package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.DTOs.ArticleAvailabilityDTO;

public interface IArticleAvailabilityService {
    ArticleAvailabilityDTO getArticleAvailabilityDTP(long articleId) throws Exception;
}
