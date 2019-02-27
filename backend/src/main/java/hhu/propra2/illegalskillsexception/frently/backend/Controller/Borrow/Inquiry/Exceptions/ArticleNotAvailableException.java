package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class ArticleNotAvailableException extends FrentlyException {
    public ArticleNotAvailableException() {
        super("The selected borrowArticle is not available in the requested period.", FrentlyErrorType.ARTICLE_NOT_AVAILABLE);
    }
}
