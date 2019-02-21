package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserDetailService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Services.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.ForeignUserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.UserDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailService implements IUserDetailService {

    private final IApplicationUserService applicationUserService;

    private final IUserTransactionService userTransactionService;

    //private final IPropayService propayService

    public UserDetailResponse getUserDetails(Authentication auth) {

        UserDetailResponse userDetails = new UserDetailResponse();

        ApplicationUser currentUser = applicationUserService.getCurrentUser(auth);
        userDetails.setEmail(currentUser.getEmail());
        userDetails.setUsername(currentUser.getUsername());
        //userDetails.setPropayUsername(currentUser.getBankAccount());

        List<Transaction> finishedTransaction = userTransactionService.getAllFinishedTransactions(currentUser);
        userDetails.setCompletedTransactions(finishedTransaction);

        //@TODO Deal with propay account
        //double accountBalance = propayService.getCurrentBalance(currentUser);
        //userDetails.setAccountBalance(accountBalance);

        return userDetails;
    }


    public ForeignUserDetailResponse getForeignUserDetails(String username) throws UserNotFoundException {

        ForeignUserDetailResponse foreignUserDetailResponse = new ForeignUserDetailResponse();

        ApplicationUser foreignUser = applicationUserService.getApplicationUserByUsername(username);

        if (foreignUser == null) {
            throw new UserNotFoundException("User not found", FrentlyErrorType.USER_NOT_FOUND);
        }
        foreignUserDetailResponse.setUsername(foreignUser.getUsername());

        List<Transaction> finishedTransaction = userTransactionService.getAllFinishedTransactions(foreignUser);
        foreignUserDetailResponse.setCompletedTransactions(finishedTransaction);

        //@TODO Deal with propay account
        //double accountBalance = propayService.getCurrentBalance(currentUser);
        //userDetails.setAccountBalance(accountBalance);

        return foreignUserDetailResponse;
    }
}
