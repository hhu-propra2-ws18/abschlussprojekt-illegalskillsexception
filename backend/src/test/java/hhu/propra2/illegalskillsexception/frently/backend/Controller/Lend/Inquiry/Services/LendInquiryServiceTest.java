package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class LendInquiryServiceTest {

    private LendInquiryService inquiryService;
    private Inquiry inquiry0 = new Inquiry();
    private Inquiry inquiry1 = new Inquiry();
    private Inquiry inquiry2 = new Inquiry();
    private Inquiry inquiry3 = new Inquiry();
    private LocalDate nowDate = LocalDate.of(2018, Month.FEBRUARY, 25);
    
    @Before
    public void setUp() {
        IInquiryRepository inquiryRepository = mock(IInquiryRepository.class);
        inquiryService = new LendInquiryService(inquiryRepository);
    }

    @Test
    public void noOpenInquiries() {
        inquiry0.setStatus(Inquiry.Status.ACCEPTED);
        inquiry1.setStatus(Inquiry.Status.DECLINED);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1);

        List<Inquiry> openInquiries = inquiryService.getOpenInquiries(inquiryList);

        assertEquals(0, openInquiries.size());
    }

    @Test
    public void oneOpenInquirie() {
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.DECLINED);
        inquiry2.setStatus(Inquiry.Status.ACCEPTED);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1, inquiry2);

        List<Inquiry> openInquiries = inquiryService.getOpenInquiries(inquiryList);

        assertEquals(1, openInquiries.size());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(0).getStatus());
    }

    @Test
    public void twoOpenInquiries() {
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.DECLINED);
        inquiry2.setStatus(Inquiry.Status.ACCEPTED);
        inquiry3.setStatus(Inquiry.Status.OPEN);
        List<Inquiry> inquiryList = Arrays.asList(inquiry0, inquiry1, inquiry2, inquiry3);

        List<Inquiry> openInquiries = inquiryService.getOpenInquiries(inquiryList);

        assertEquals(2, openInquiries.size());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(0).getStatus());
        assertEquals(Inquiry.Status.OPEN, openInquiries.get(1).getStatus());
    }

    @Test
    public void noInquiries() {
        LocalDate nowDate = LocalDate.of(2018, Month.FEBRUARY, 25);
        List<Inquiry> noInquiries = new ArrayList<>();
        List<Inquiry> result = inquiryService.removeExpiredInquires(noInquiries, nowDate);

        assertEquals(0, result.size());
    }

    @Test
    public void noExpiredInquiresWereRemoved() {
        LocalDate sameDate = LocalDate.of(2018, Month.FEBRUARY, 25);
        LocalDate afterDate = LocalDate.of(2018, Month.FEBRUARY, 26);
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.OPEN);
        inquiry0.setStartDate(sameDate);
        inquiry1.setStartDate(afterDate);
        List<Inquiry> notExpired = Arrays.asList(inquiry0, inquiry1);

        List<Inquiry> result = inquiryService.removeExpiredInquires(notExpired, nowDate);

        assertEquals(2, result.size());
        assertEquals(Inquiry.Status.OPEN, inquiry0.getStatus());
        assertEquals(Inquiry.Status.OPEN, inquiry1.getStatus());

    }

    @Test
    public void oneExpiredInquiresIsRemoved() {
        LocalDate expiredDate = LocalDate.of(2018, Month.FEBRUARY, 24);
        LocalDate afterDate = LocalDate.of(2019, Month.FEBRUARY, 20);
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.OPEN);
        inquiry0.setStartDate(expiredDate);
        inquiry1.setStartDate(afterDate);
        List<Inquiry> oneExpired = Arrays.asList(inquiry0, inquiry1);

        List<Inquiry> result = inquiryService.removeExpiredInquires(oneExpired, nowDate);

        assertEquals(1, result.size());
        assertEquals(Inquiry.Status.DECLINED, inquiry0.getStatus());
        assertEquals(Inquiry.Status.OPEN, inquiry1.getStatus());
    }

    @Test
    public void multipleExpiredInquiresWereRemoved() {
        LocalDate expiredDate0 = LocalDate.of(2017, Month.JULY, 14);
        LocalDate expiredDate1 = LocalDate.of(2015, Month.DECEMBER, 24);
        LocalDate afterDate = LocalDate.of(2019, Month.FEBRUARY, 20);
        inquiry0.setStatus(Inquiry.Status.OPEN);
        inquiry1.setStatus(Inquiry.Status.OPEN);
        inquiry2.setStatus(Inquiry.Status.OPEN);
        inquiry0.setStartDate(expiredDate0);
        inquiry1.setStartDate(expiredDate1);
        inquiry2.setStartDate(afterDate);
        List<Inquiry> twoExpired = Arrays.asList(inquiry0, inquiry1, inquiry2);

        List<Inquiry> result = inquiryService.removeExpiredInquires(twoExpired, nowDate);

        assertEquals(1, result.size());
        assertEquals(Inquiry.Status.DECLINED, inquiry0.getStatus());
        assertEquals(Inquiry.Status.DECLINED, inquiry1.getStatus());
        assertEquals(Inquiry.Status.OPEN, inquiry2.getStatus());

    }

}