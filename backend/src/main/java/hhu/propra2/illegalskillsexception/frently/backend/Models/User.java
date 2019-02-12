package hhu.propra2.illegalskillsexception.frently.backend.Models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String email;
    private String username;
    private String bankAccount;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updated;
}
