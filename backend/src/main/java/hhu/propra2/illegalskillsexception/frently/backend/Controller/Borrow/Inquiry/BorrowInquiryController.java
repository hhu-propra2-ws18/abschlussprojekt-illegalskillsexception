package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.DTOs.BorrowInquiryDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.IServices.IBorrowInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/borrow/inquiry")
public class BorrowInquiryController {
    private final IBorrowInquiryService inquiryService;

    @GetMapping("/")
    public FrentlyResponse retrieveAllMyInquiries(Authentication auth) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            List<Inquiry> inquiries = inquiryService.retrieveAllInquiriesByUser(auth);
            response.setData(inquiries);
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }

    @PostMapping("/create")
    public FrentlyResponse createInquiry(Authentication auth, @RequestBody BorrowInquiryDTO borrowInquiryDTO) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            Inquiry inquiry = inquiryService.createInquiry(auth, borrowInquiryDTO);
            response.setData(inquiry);
        } catch (FrentlyException fe) {
            response.setError(new FrentlyError(fe));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }
}
