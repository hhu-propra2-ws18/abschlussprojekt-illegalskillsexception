package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices.ILendInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class LendInquiryServiceTest {

    private ILendInquiryService inquiryService;

    @Before
    public void setUp() {
        IInquiryRepository inquiryRepository = mock(IInquiryRepository.class);
        inquiryService = new LendInquiryService(inquiryRepository);
    }

    @Test
    public void noOpenInquiries() {
        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        inquiry0.setStatus(Inquiry.Status.ACCEPTED);
        inquiry1.setStatus(Inquiry.Status.DECLINED);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1);

        List<Inquiry> openInquiries = inquiryService.filterOpenInquiries(inquiryList);

        assertEquals(0, openInquiries.size());
    }

    @Test
    public void oneOpenInquiries() {
        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        Inquiry inquiry2 = new Inquiry();
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.DECLINED);
        inquiry2.setStatus(Inquiry.Status.ACCEPTED);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1, inquiry2);

        List<Inquiry> openInquiries = inquiryService.filterOpenInquiries(inquiryList);

        assertEquals(1, openInquiries.size());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(0).getStatus());
    }

    @Test
    public void twoOpenInquiries() {
        Inquiry inquiry0 = new Inquiry();
        Inquiry inquiry1 = new Inquiry();
        Inquiry inquiry2 = new Inquiry();
        Inquiry inquiry3 = new Inquiry();
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.DECLINED);
        inquiry2.setStatus(Inquiry.Status.ACCEPTED);
        inquiry3.setStatus(Inquiry.Status.OPEN);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1, inquiry2, inquiry3);

        List<Inquiry> openInquiries = inquiryService.filterOpenInquiries(inquiryList);

        assertEquals(2, openInquiries.size());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(0).getStatus());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(1).getStatus());
    }
}