package hhu.propra2.illegalskillsexception.frently.backend.Controller.Security.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class NotAdminException extends FrentlyException {
    public NotAdminException() {
        super("You shall not pass!", FrentlyErrorType.USER_IS_NOT_ADMIN);
    }
}




