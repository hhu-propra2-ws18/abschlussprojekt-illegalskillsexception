package hhu.propra2.illegalskillsexception.frently.backend.Models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionResponseExport {

    private boolean isLender;
    private String title;
    private String status;
    private String location;
    private LocalDate returnDate;
}
