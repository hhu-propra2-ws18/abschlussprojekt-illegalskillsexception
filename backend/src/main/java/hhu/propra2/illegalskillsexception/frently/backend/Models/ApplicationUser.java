package hhu.propra2.illegalskillsexception.frently.backend.Models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String email;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String bankAccount;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updated;


}
