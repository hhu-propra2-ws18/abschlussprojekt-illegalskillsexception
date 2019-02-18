package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.LendingPeriod;
import hhu.propra2.illegalskillsexception.frently.backend.Services.IInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inquiry")
public class InquiryController {

    private IInquiryService inquiryService;

    @Autowired
    public InquiryController(IInquiryService iInquiryService) {
        this.inquiryService = iInquiryService;
    }

    @GetMapping("/getAll")
    public FrentlyResponse returnEveryInquiryOf(Authentication authentication) {
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        FrentlyResponse fr = new FrentlyResponse();
        List<Inquiry> list = inquiryService.getAllInquiries(user.getId());
        fr.setData(list);
        FrentlyError e = new FrentlyError("",null);
        fr.setError(e);
        return fr;
    }

    // TODO Complete the method
    /*
    @GetMapping("/accept")
    public FrentlyResponse acceptInquiry(Authentication authentication, @RequestParam Long inquiry_id) {
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        Inquiry inquiry = inquiryService.getInquiry(inquiry_id);

        long prize = calculatePrize(inquiry);

        return null;
    }*/

    private Long calculatePrize(Inquiry inquiry) {
        Article article  = inquiry.getArticle();
        long deposit = article.getDeposit();
        long dailyRate = article.getDailyRate();
        LendingPeriod lendingPeriod = inquiry.getDuration();
        long length = lendingPeriod.getLengthInDays();

        return deposit + dailyRate * length;
    }
}
