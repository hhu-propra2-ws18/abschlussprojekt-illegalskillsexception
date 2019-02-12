package hhu.propra2.illegalskillsexception.frently.backend.Models;

import java.time.LocalDateTime;

public class Article {
    private long id;
    private String title;
    private long owner;
    private int deposit;
    private int description;
    private int dailyRate;
    private LocalDateTime timestamp;
    private LocalDateTime updated;
}
