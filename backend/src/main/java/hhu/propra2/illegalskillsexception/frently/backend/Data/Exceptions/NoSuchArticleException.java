package hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class NoSuchArticleException extends FrentlyException {
    public NoSuchArticleException() {
        super("The requested article does not exist.", FrentlyErrorType.NOT_SUCH_ARTICLE);
    }
}
