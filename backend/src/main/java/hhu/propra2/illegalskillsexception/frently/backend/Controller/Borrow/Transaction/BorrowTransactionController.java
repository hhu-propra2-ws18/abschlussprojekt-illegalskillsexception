package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.DTOs.ReturnItemRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.DTOs.ReturnItemResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.IServices.IBorrowTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/borrow/transaction")
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

    @RequestMapping("/return")
    public FrentlyResponse returnItem(@RequestBody ReturnItemRequestDTO dto) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            Transaction transaction = transactionService.updateTransaction(dto);
            response.setData(new ReturnItemResponseDTO(transaction.getStatus()));
        } catch (FrentlyException fe) {
            response.setError(new FrentlyError(fe));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }
}
