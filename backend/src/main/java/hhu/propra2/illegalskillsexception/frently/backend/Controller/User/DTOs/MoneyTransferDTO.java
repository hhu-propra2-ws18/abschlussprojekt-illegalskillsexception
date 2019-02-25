package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs;

import lombok.Data;

@Data
public class MoneyTransferDTO {

    private String sender;
    private String receiver;

    private double amount;
}
