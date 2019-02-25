package hhu.propra2.illegalskillsexception.frently.backend.Controller.User;


import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.ChargeAmountDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.ForeignUserDetailRequest;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.ForeignUserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.UserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserAlreadyExistsAuthenticationException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserDetailService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
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
    private IUserTransactionService transactionService;
    private IProPayService proPayService;

    @PostMapping("/sign-up")
    public FrentlyResponse signUp(@RequestBody ApplicationUser user) {
        FrentlyResponse response = new FrentlyResponse();
        userService.encryptPassword(user);
        try {
            userService.createUser(user);

            proPayService.createAccount(user.getUsername(),0);

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

    @PostMapping("/charge")
    public FrentlyResponse chargeCredit(Authentication auth, @RequestBody ChargeAmountDTO amount){
        FrentlyResponse fr = new FrentlyResponse();
        String userName = (String)auth.getPrincipal();
        proPayService.payInMoney(userName,amount.getAmount());
        return fr;
    }

    @GetMapping("/notifications")
    public FrentlyResponse notifications(Authentication auth) {
        FrentlyResponse response = new FrentlyResponse();
        ApplicationUser currentUser = userService.getCurrentUser(auth);
        try {
            response.setData(transactionService.allOverdueTransactions(currentUser.getId()));
        } catch (Exception e) { //TODO: Better Exception Handling
            response.setError(new FrentlyError("Couldn't find any Transactions overdue for owner", FrentlyErrorType.NO_SUCH_TRANSACTION));
        }
        return response;
    }
}