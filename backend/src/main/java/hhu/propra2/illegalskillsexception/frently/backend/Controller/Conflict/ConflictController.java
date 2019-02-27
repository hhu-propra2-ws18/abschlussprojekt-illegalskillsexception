package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.DTOs.TransactionIdDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices.IProcessConflictService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices.IConflictAdminService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices.IConflictService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.TransactionUpdateResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/conflict")
public class ConflictController {

    private final IConflictService conflictService;
    private final IApplicationUserService userService;
    private final IConflictAdminService conflictAdminService;
    private final IProcessConflictService processConflictService;

    @GetMapping("/")
    public FrentlyResponse retrieveConflictTransactions(Authentication authentication) {

        ApplicationUser user = userService.getCurrentUser(authentication);
        FrentlyResponse response = new FrentlyResponse();

        try {
            conflictAdminService.isAdmin(user.getRoles());
            final List<Transaction> conflictingTransList = conflictService.retrieveAllConflictingTransactions();
            response.setData(conflictingTransList);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError(e);
            response.setError(error);
        }
        return response;
    }

    @PostMapping("/release")
    public FrentlyResponse releaseConflictingTransaction(Authentication authentication, @RequestBody TransactionIdDTO transDTO) {
        ApplicationUser user = userService.getCurrentUser(authentication);
        FrentlyResponse response = new FrentlyResponse();

        try {
            conflictAdminService.isAdmin(user.getRoles());
            Transaction transaction = processConflictService.releaseConflictingTransaction(transDTO.getTransactionId());
            response.setData(new TransactionUpdateResponseDTO(transaction.getStatus()));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }

    @PostMapping("/punish")
    public FrentlyResponse punishConflictingTransaction(Authentication authentication, @RequestBody TransactionIdDTO transDTO) {
        ApplicationUser user = userService.getCurrentUser(authentication);
        FrentlyResponse response = new FrentlyResponse();

        try {
            conflictAdminService.isAdmin(user.getRoles());
            Transaction transaction = processConflictService.punishConflictingTransaction(transDTO.getTransactionId());
            response.setData(new TransactionUpdateResponseDTO(transaction.getStatus()));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }
}
