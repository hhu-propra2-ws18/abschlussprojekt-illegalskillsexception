package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.DTOs.BorrowInquiryDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.DTOs.BorrowInquiryResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.IServices.IBorrowInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services.ApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
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
    private final ApplicationUserService userService;

    @GetMapping("/")
    public FrentlyResponse retrieveAllMyInquiries(Authentication auth) {
        ApplicationUser user = userService.getCurrentUser(auth);

        FrentlyResponse response = new FrentlyResponse();
        try {
            final List<BorrowInquiryResponseDTO> inquiryList = inquiryService.retrieveAllInquiriesByUser(user);
            response.setData(inquiryList);
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
