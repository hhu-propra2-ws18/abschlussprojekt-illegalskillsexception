package hhu.propra2.illegalskillsexception.frently.backend.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String email;
    private String username;
    private String bankAccount;
    private LocalDateTime timestamp;
    private LocalDateTime updated;
}
