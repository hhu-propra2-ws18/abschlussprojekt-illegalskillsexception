package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models;

import lombok.Data;

import java.util.List;

@Data
public class ProPayAccount {
    private String account;
    private Double amount;
    private List<Reservation> reservations;
}
