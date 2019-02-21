package hhu.propra2.illegalskillsexception.frently.backend.User.DTOs;


import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailResponse {

    private String username;
    private String email;

    private String propayUsername;
    private double accountBalance;

    private List<Transaction> completedTransactions;
}
