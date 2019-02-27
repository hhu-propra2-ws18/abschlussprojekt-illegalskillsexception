package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class NoSuchTransactionException extends FrentlyException {
    public NoSuchTransactionException() {
        super("The requested Transaction does not exist.", FrentlyErrorType.NO_SUCH_TRANSACTION);
    }
}
