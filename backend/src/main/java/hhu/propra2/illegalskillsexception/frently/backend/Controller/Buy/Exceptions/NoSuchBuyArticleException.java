package hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class NoSuchBuyArticleException extends FrentlyException {
    public NoSuchBuyArticleException() {
        super("The selected borrowArticle is not available.", FrentlyErrorType.NO_SUCH_SELL_ARTICLE);
    }
}
