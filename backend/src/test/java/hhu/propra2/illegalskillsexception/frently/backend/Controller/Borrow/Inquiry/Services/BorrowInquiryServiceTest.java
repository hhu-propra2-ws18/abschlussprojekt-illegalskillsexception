package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IBorrowArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.IServices.IBorrowInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices.ILendInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.Services.LendInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IApplicationUserRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BorrowInquiryServiceTest {


    private IBorrowInquiryService inquiryService;

    @Before
    public void setUp() {
        IInquiryRepository inquiryRepository = mock(IInquiryRepository.class);
        IApplicationUserService userService = mock(IApplicationUserService.class);
        IBorrowArticleService articleService = mock(IBorrowArticleService.class);
        inquiryService = new BorrowInquiryService(inquiryRepository, userService, articleService);
    }

    @Test
    public void noOpenOrAcceptedInquiries() {
        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        inquiry0.setStatus(Inquiry.Status.DECLINED);
        inquiry1.setStatus(Inquiry.Status.DECLINED);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1);

        List<Inquiry> openInquiries = inquiryService.getOpenAndAcceptedInquiries(inquiryList);

        assertEquals(0, openInquiries.size());
    }

    @Test
    public void onlyOpenInquiries() {
        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        Inquiry inquiry2 = new Inquiry();
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.OPEN);
        inquiry2.setStatus(Inquiry.Status.DECLINED);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1, inquiry2);

        List<Inquiry> openInquiries = inquiryService.getOpenAndAcceptedInquiries(inquiryList);

        assertEquals(2, openInquiries.size());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(0).getStatus());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(1).getStatus());
    }

    @Test
    public void onlyAcceptedInquiries() {
        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        Inquiry inquiry2 = new Inquiry();
        inquiry0.setStatus(Inquiry.Status.ACCEPTED);
        inquiry1.setStatus(Inquiry.Status.ACCEPTED);
        inquiry2.setStatus(Inquiry.Status.DECLINED);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1, inquiry2);

        List<Inquiry> openInquiries = inquiryService.getOpenAndAcceptedInquiries(inquiryList);

        assertEquals(2, openInquiries.size());
        assertEquals(Inquiry.Status.ACCEPTED, openInquiries.get(0).getStatus());
        assertEquals(Inquiry.Status.ACCEPTED, openInquiries.get(1).getStatus());
    }

    @Test
    public void openAndAcceptedInquiries() {
        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        Inquiry inquiry2 = new Inquiry();
        Inquiry inquiry3 = new Inquiry();
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.ACCEPTED);
        inquiry2.setStatus(Inquiry.Status.ACCEPTED);
        inquiry3.setStatus(Inquiry.Status.DECLINED);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1, inquiry2, inquiry3);

        List<Inquiry> openInquiries = inquiryService.getOpenAndAcceptedInquiries(inquiryList);

        assertEquals(3, openInquiries.size());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(0).getStatus());
        assertEquals(Inquiry.Status.ACCEPTED, openInquiries.get(1).getStatus());
        assertEquals(Inquiry.Status.ACCEPTED, openInquiries.get(2).getStatus());
    }
}