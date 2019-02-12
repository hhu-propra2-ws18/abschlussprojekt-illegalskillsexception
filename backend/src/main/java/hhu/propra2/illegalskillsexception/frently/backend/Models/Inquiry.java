package hhu.propra2.illegalskillsexception.frently.backend.Models;

import java.time.LocalDateTime;
import java.time.Period;

public class Inquiry {
    public enum State {

    }

    private long id;
    private Article article;
    private User borrower;
    private User lender;
    private Period duration;
    private State state;
    private LocalDateTime timestamp;
    private LocalDateTime updated;
}
