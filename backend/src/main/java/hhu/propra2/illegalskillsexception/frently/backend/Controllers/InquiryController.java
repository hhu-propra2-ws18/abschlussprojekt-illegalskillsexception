package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryController {

    private IInquiryService inquiryService;

    @Autowired
    public InquiryController(IInquiryService iInquiryService) {
        this.inquiryService = iInquiryService;
    }

    @GetMapping("/getAll")
    public FrentlyResponse returnEveryInquiryOf(@RequestBody ApplicationUser user){
        FrentlyResponse fr = new FrentlyResponse();
        List<Inquiry> list = inquiryService.getAllInquiries(user.getId());
        fr.setData(list);
        FrentlyError e = new FrentlyError("",null);
        fr.setError(e);
        return fr;
    }
    //TODO map accept
    /*
    @GetMapping("/accept")
    public FrentlyResponse acceptInquiry(@RequestBody ApplicationUser user, @RequestParam Inquiry_id) {
        FrentlyResponse
    }
    */
}
