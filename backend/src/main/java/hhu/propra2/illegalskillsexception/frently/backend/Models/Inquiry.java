package hhu.propra2.illegalskillsexception.frently.backend.Models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Article article;
    @OneToOne
    private ApplicationUser borrower;
    @OneToOne
    private ApplicationUser lender;
    @Embedded
    @Column(nullable = false)
    private LendingPeriod duration;
    @Embedded
    private Status status;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updated;

    @Embeddable
    public enum Status {
        open, declined, accepted
    }
}
