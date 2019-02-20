package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Inquiry.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowInquiryDTO {
    private long articleId;
    private LocalDate startDate;
    private LocalDate endDate;

    public void setStartDate(String dateString) {
        this.startDate = LocalDate.parse(dateString);
    }

    public void setEndDate(String dateString) {
        this.endDate = LocalDate.parse(dateString);
    }
}
