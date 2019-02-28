package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class ArticleNotReturnedException extends FrentlyException {
    public ArticleNotReturnedException() {
        super("The article must be returned by the borrower", FrentlyErrorType.ARTICLE_MUST_BE_RETURNED);
    }
}
