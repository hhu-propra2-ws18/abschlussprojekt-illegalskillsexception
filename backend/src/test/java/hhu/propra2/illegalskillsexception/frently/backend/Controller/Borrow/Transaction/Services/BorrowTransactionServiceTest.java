package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.Services;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class BorrowTransactionServiceTest {

    private final BorrowTransactionService transactionService = new BorrowTransactionService(null, null, null);

    @Test
    public void calculateFeeForOneDay() {
        LocalDate date = LocalDate.of(2019, 12, 12);
        double actualFee = transactionService.calculateFee(date, date, 10.);
        assertEquals(10., actualFee, 0.00001);
    }

    @Test
    public void calculateFeeForOneWeek() {
        LocalDate date = LocalDate.of(2019, 12, 12);
        double actualFee = transactionService.calculateFee(date, date.plusWeeks(1), 10.);
        assertEquals(80., actualFee, 0.00001);
    }
}