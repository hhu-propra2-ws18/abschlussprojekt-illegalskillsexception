package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class UserNotFoundException extends FrentlyException {
    public UserNotFoundException() {
        super("The requested user could not be found.", FrentlyErrorType.USER_NOT_FOUND);
    }
}
