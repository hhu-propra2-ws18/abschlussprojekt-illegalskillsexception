package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class UserNotFoundException extends FrentlyException {
    public UserNotFoundException(String msg, FrentlyErrorType type) {
        super(msg, type);
    }
}
