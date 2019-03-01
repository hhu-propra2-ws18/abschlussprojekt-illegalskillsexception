package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class InsufficientFundsException extends FrentlyException {
    public InsufficientFundsException() {
        super("The borrower currently has not enough money.", FrentlyErrorType.INSUFFICIENT_FUNDS);
    }
}
