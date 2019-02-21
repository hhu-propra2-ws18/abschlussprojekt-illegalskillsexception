package hhu.propra2.illegalskillsexception.frently.backend.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;

public class NoSuchArticleException extends FrentlyException {
    public NoSuchArticleException() {
        super("The requested article does not exist.", FrentlyErrorType.NOT_SUCH_ARTICLE);
    }
}
