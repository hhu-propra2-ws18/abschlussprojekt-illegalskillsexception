package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class BorrowerNotEnoughMoneyException extends FrentlyException {
    public BorrowerNotEnoughMoneyException() {
        super("The borrower currently has not enough money.", FrentlyErrorType.NOT_ENOUGH_MONEY);
    }
}
