package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistsAuthenticationException extends AuthenticationException {
    public UserAlreadyExistsAuthenticationException(String msg) {
        super(msg);
    }
}
