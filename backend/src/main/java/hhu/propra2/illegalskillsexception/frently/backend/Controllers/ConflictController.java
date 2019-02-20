package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction.Status;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conflict")
@AllArgsConstructor
public class ConflictController {

    private final IApplicationUserService userService;
    private final TransactionService transactionService;

    @GetMapping("/getAll")
    public FrentlyResponse getAllConflicts(Authentication authentication) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            response.setData(transactionService.getAllConflicts());
        } catch (Exception e) {
            FrentlyError error = new FrentlyError("Could not extract Conflicts", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }

    @PostMapping("/resolve")
    public FrentlyResponse resolveConflict(Authentication authentication,
                                           @RequestParam long id, @RequestParam double resolvedDeposit) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            transactionService.updateTransactionStatus(transactionService.getTransaction(id), Status.closed);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError("Could not update status of conflict", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }
}
