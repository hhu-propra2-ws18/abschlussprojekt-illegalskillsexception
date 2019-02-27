package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.DTOs;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LendInquiryResponseDTO {
    private long id;
    private BorrowArticle borrowArticle;
    private ApplicationUser borrower;
    private ApplicationUser lender;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime timestamp;
    private LocalDateTime updated;

    public LendInquiryResponseDTO(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.borrowArticle = inquiry.getBorrowArticle();
        this.borrower = inquiry.getBorrower();
        this.lender = inquiry.getLender();
        this.startDate = inquiry.getStartDate();
        this.endDate = inquiry.getEndDate();
        this.timestamp = inquiry.getTimestamp();
        this.updated = inquiry.getUpdated();

        this.status = inquiry.getStatus().toString();
    }
}
