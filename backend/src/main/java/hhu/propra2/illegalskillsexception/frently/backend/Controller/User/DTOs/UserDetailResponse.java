package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs;


import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailResponse {

    private String username;
    private String email;

    private String propayUsername;
    private double accountBalance;

    private List<MoneyTransferDTO> completedTransactions;
}
