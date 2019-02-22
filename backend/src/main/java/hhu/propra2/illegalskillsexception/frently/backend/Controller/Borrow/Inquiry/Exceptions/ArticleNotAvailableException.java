package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;

public class ArticleNotAvailableException extends FrentlyException {
    public ArticleNotAvailableException() {
        super("The selected article is not available in the requested period.", FrentlyErrorType.ARTICLE_NOT_AVAILABLE);
    }
}
