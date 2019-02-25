package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class ForeignUserDetailResponse {

    private String username;

    private List<MoneyTransferDTO> completedTransactions;
}
