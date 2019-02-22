package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class BorrowerHasNotEnoughMoneyException extends FrentlyException {
    public BorrowerHasNotEnoughMoneyException() {
        super("Borrower has not enough money", FrentlyErrorType.INSUFFICIENT_FUNDS);
    }
}
