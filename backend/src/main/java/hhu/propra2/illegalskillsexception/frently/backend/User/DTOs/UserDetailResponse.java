package hhu.propra2.illegalskillsexception.frently.backend.User.DTOs;


import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailResponse {

    public String username;
    public String email;

    public String propayUsername;
    public double accountBalance;

    public List<Transaction> completedTransactions;
}
