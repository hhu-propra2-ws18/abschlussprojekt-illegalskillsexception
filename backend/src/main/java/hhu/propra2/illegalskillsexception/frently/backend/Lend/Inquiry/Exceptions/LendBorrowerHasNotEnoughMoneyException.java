package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.FrentlyException;

public class LendBorrowerHasNotEnoughMoneyException extends FrentlyException {
    public LendBorrowerHasNotEnoughMoneyException() {
        super("Borrower has not enough money", FrentlyErrorType.INSUFFICIENT_FUNDS);
    }
}
