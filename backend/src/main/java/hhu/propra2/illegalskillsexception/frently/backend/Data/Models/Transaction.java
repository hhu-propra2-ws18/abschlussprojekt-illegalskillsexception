package hhu.propra2.illegalskillsexception.frently.backend.Data.Models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
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
        OPEN, CLOSED, CONFLICT, PENDING_PAYMENT
    }
}
