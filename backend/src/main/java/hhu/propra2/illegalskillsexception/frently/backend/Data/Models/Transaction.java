package hhu.propra2.illegalskillsexception.frently.backend.Data.Models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Inquiry inquiry;
    private LocalDate returnDate;
    @Embedded
    private Status status;
    private long reservationId;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updated;


    @Embeddable
    public enum Status {
        open, closed, conflict, MONEY_CONFLICT
    }
}
