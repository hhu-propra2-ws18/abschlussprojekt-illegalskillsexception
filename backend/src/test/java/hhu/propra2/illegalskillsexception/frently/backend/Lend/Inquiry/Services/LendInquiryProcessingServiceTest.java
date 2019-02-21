package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.Services;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class LendInquiryProcessingServiceTest {

    private final LendInquiryProcessingService lendInquiryProcessingService = new LendInquiryProcessingService();

    @Test
    public void calculateFeeForOneDay() {
        LocalDate date = LocalDate.of(2019, 12, 12);
        Double actualFee = lendInquiryProcessingService.calculateFee(date, date, 10.);
        assertEquals(10., actualFee, 0.00001);
    }

    @Test
    public void calculateFeeForOneWeek() {
        LocalDate date = LocalDate.of(2019, 12, 12);
        Double actualFee = lendInquiryProcessingService.calculateFee(date, date.plusWeeks(1), 10.);
        assertEquals(80., actualFee, 0.00001);
    }
}