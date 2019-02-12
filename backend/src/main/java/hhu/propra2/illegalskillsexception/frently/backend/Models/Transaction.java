package hhu.propra2.illegalskillsexception.frently.backend.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Inquiry inquiry;
    private LocalDateTime returnDate;
}
