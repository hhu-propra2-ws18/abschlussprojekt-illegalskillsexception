package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import lombok.Data;

import java.util.List;

@Data
public class ForeignUserDetailResponse {

    private String username;

    private List<MoneyTransferDTO> completedTransactions;
}
