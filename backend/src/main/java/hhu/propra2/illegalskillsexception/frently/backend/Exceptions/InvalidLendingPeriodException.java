package hhu.propra2.illegalskillsexception.frently.backend.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;

public class InvalidLendingPeriodException extends FrentlyException {
    public InvalidLendingPeriodException() {
        super("The supplied combination of start and end date is invalid.", FrentlyErrorType.INVALID_LENDING_PERIOD);
    }
}
