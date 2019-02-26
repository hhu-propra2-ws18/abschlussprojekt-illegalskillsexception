package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.DTOs;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import lombok.Data;

@Data
public class ReturnItemResponseDTO {
    private String status;

    public ReturnItemResponseDTO(Transaction.Status status) {
        this.status = status.toString();
    }
}
