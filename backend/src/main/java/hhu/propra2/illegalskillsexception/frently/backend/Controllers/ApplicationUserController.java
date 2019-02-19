package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.UserAlreadyExistsAuthenticationException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    private ApplicationUserService userService;

    @PostMapping("/sign-up")
    public FrentlyResponse signUp(@RequestBody ApplicationUser user) {

        FrentlyResponse response = new FrentlyResponse();
        userService.encryptPassword(user);
        try {
            userService.createUser(user);

            response.setData(Collections.singletonList(user));
        } catch (UserAlreadyExistsAuthenticationException e) {
            response.setError(new FrentlyError(e.getMessage(), FrentlyErrorType.MISC));
        }
        return response;
    }
}
