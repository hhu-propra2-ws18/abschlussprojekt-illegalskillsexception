package hhu.propra2.illegalskillsexception.frently.backend.Controller.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;

public class InvalidLendingPeriodException extends FrentlyException {
    public InvalidLendingPeriodException() {
        super("The supplied combination of start and end date is invalid.", FrentlyErrorType.INVALID_LENDING_PERIOD);
    }
}
