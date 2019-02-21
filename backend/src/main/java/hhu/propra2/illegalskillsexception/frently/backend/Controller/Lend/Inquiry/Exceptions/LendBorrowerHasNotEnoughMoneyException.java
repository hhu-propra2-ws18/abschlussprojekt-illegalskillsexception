package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Exceptions.FrentlyException;

public class LendBorrowerHasNotEnoughMoneyException extends FrentlyException {
    public LendBorrowerHasNotEnoughMoneyException() {
        super("Borrower has not enough money", FrentlyErrorType.INSUFFICIENT_FUNDS);
    }
}
