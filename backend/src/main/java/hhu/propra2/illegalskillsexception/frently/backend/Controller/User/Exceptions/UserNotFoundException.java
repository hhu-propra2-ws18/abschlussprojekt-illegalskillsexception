package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;

public class UserNotFoundException extends FrentlyException {
    public UserNotFoundException(String msg, FrentlyErrorType type) {
        super(msg, type);
    }
}