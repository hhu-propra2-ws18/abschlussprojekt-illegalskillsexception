package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;
    private ApplicationUserService userService;

    @GetMapping("/getAll")
    public FrentlyResponse getAllTransactionsForOwner(Authentication authentication) {
        FrentlyResponse response = new FrentlyResponse();
        ApplicationUser user = userService.getCurrentUser(authentication);
        try {
            response.setData(transactionService.exportTransactions(user.getId()));
        } catch (Exception e) {
            FrentlyError error = new FrentlyError("Could not extract transactions from user", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }

    @PostMapping("/itemBorrowerReturn")
    public FrentlyResponse returnArticle(Authentication authentication, @RequestParam long id) {
        //TODO
        return new FrentlyResponse();
    }

    @PostMapping("/problem")
    public FrentlyResponse conflict(Authentication authentication, @RequestParam long id) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            transactionService.updateTransactionStatus(transactionService.getTransaction(id), Transaction.Status.conflict);
            response.setData("" + Transaction.Status.conflict);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError("Could not set conflict status", FrentlyErrorType.MISC);
            response.setError(error);
        }
        return response;
    }
}
