package hhu.propra2.illegalskillsexception.frently.backend.User.DTOs;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class ForeignUserDetailResponse {

    private String username;

    private List<Transaction> completedTransactions;
}
