package hhu.propra2.illegalskillsexception.frently.backend.Exceptions;

public class ArticleNotAvailableException extends Exception { // TODO: better Exception class to extend?
    public ArticleNotAvailableException() {
        super("The selected article is not available in the requested period.");
    }
}
