package hhu.propra2.illegalskillsexception.frently.backend.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Reservation {

    @GeneratedValue
    @Id
    private long id;

    private double amount;
    private long propayId;
}
