package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.IServices.IBorrowTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/borrow/transacton")
public class BorrowTransactionController {

    IBorrowTransactionService transactionService;

    @RequestMapping("/")
    public FrentlyResponse retrieveAllOfUser(Authentication auth) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            response.setData(transactionService.retrieveAllOfCurrentUser(auth));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }
}
