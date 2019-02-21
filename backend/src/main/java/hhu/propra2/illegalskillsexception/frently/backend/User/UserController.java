package hhu.propra2.illegalskillsexception.frently.backend.User;


import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.UserAlreadyExistsAuthenticationException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.User.DTOs.ForeignUserDetailRequest;
import hhu.propra2.illegalskillsexception.frently.backend.User.DTOs.ForeignUserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.User.DTOs.UserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.User.Services.IUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private IApplicationUserService userService;
    private IUserDetailService userDetailService;

    @PostMapping("/sign-up")
    public FrentlyResponse signUp(@RequestBody ApplicationUser user) {
        FrentlyResponse response = new FrentlyResponse();
        userService.encryptPassword(user);
        try {
            userService.createUser(user);

            response.setData(Collections.singletonList(user));
        } catch (UserAlreadyExistsAuthenticationException e) {
            response.setError(new FrentlyError(e.getMessage(), FrentlyErrorType.USER_ALREADY_EXISTING));
        }
        return response;
    }

    @GetMapping("/")
    public FrentlyResponse getUserDetails(Authentication auth) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            UserDetailResponse userDetailResponse = userDetailService.getUserDetails(auth);
            response.setData(userDetailResponse);
        } catch (FrentlyException exc) {
            response.setError(new FrentlyError(exc));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }

    @PostMapping("/")
    public FrentlyResponse getUserDetails(@RequestBody ForeignUserDetailRequest request) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            ForeignUserDetailResponse foreignUserDetailResponse = userDetailService.getForeignUserDetails(request.getUsername());
            response.setData(foreignUserDetailResponse);
        } catch (FrentlyException exc) {
            response.setError(new FrentlyError(exc));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }


}