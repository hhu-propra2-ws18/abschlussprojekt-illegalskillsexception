package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MoneyTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private ApplicationUser sender;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private ApplicationUser receiver;

    private double amount;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime timestamp;
}
