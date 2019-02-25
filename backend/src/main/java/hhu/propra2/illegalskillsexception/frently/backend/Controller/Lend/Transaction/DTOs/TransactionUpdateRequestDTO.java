package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs;

import lombok.Data;

@Data
public class TransactionUpdateRequestDTO {

    private long transactionId;
    private boolean isFaulty;
}
