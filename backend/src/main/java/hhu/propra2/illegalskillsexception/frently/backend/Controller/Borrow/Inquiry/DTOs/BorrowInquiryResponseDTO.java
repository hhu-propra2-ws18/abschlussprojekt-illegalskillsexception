package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.DTOs;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BorrowInquiryResponseDTO {
    private long id;
    private Article article;
    private ApplicationUser borrower;
    private ApplicationUser lender;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime timestamp;
    private LocalDateTime updated;

    public BorrowInquiryResponseDTO(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.article = inquiry.getArticle();
        this.borrower = inquiry.getBorrower();
        this.lender = inquiry.getLender();
        this.startDate = inquiry.getStartDate();
        this.endDate = inquiry.getEndDate();
        this.timestamp = inquiry.getTimestamp();
        this.updated = inquiry.getUpdated();

        this.status = inquiry.getStatus().toString();
    }
}
