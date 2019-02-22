package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.LendTransactionUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.transactionUpdateResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.IService.ILendTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lend/transaction")
@AllArgsConstructor
public class LendTransactionController {

    private final ILendTransactionService transactionService;

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

    @PostMapping("/update")
    public FrentlyResponse updateTransaction(Authentication auth, @RequestBody LendTransactionUpdate updateDTO){
        FrentlyResponse response = new FrentlyResponse();
        try{
            Transaction temp = transactionService.updateTransaction(auth, updateDTO);
            response.setData(new transactionUpdateResponseDTO(temp.getStatus()));
        } catch (FrentlyException fe) {
            response.setError(new FrentlyError(fe));
        }
        return response;
    }
}
