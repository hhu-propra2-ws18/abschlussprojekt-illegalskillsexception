package hhu.propra2.illegalskillsexception.frently.backend.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;

public class UserNotFoundException extends FrentlyException {
    public UserNotFoundException(String msg, FrentlyErrorType type) {
        super(msg, type);
    }
}
