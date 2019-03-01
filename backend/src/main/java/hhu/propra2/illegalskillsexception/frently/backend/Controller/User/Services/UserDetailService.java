package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.ForeignUserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.MoneyTransferDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.UserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserDetailService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import lombok.AllArgsConstructor;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailService implements IUserDetailService {

    private final IApplicationUserService applicationUserService;

    private final IUserTransactionService userTransactionService;

    private final IProPayService proPayService;

    public UserDetailResponse getUserDetails(Authentication auth) throws ProPayConnectionException {

        UserDetailResponse userDetails = new UserDetailResponse();

        ApplicationUser currentUser = applicationUserService.getCurrentUser(auth);
        userDetails.setEmail(currentUser.getEmail());
        userDetails.setUsername(currentUser.getUsername());

        List<MoneyTransferDTO> finishedTransaction = userTransactionService.getAllFinishedTransactions(currentUser);
        userDetails.setCompletedTransactions(finishedTransaction);

        double accountBalance;
        try {
            accountBalance = proPayService.getAccountBalance(currentUser.getUsername());
        } catch (ExhaustedRetryException e) {
            throw new ProPayConnectionException();
        }
        userDetails.setAccountBalance(accountBalance);

        return userDetails;
    }


    @Override
    public UserDetailResponse getUserDetailsWithoutPropay(Authentication auth){
        UserDetailResponse userDetailResponse = new UserDetailResponse();

        ApplicationUser currentUser = applicationUserService.getCurrentUser(auth);
        userDetailResponse.setEmail(currentUser.getEmail());
        userDetailResponse.setUsername(currentUser.getUsername());

        List<MoneyTransferDTO> finishedTransaction = userTransactionService.getAllFinishedTransactions(currentUser);
        userDetailResponse.setCompletedTransactions(finishedTransaction);
        userDetailResponse.setAccountBalance(-1);
        return userDetailResponse;
    }

    public ForeignUserDetailResponse getForeignUserDetails(String username) throws UserNotFoundException {

        ForeignUserDetailResponse foreignUserDetailResponse = new ForeignUserDetailResponse();

        ApplicationUser foreignUser = applicationUserService.getApplicationUserByUsername(username);

        if (foreignUser == null) {
            throw new UserNotFoundException();
        }
        foreignUserDetailResponse.setUsername(foreignUser.getUsername());

        List<MoneyTransferDTO> finishedTransaction = userTransactionService.getAllFinishedTransactions(foreignUser);
        foreignUserDetailResponse.setCompletedTransactions(finishedTransaction);

        return foreignUserDetailResponse;
    }
}
