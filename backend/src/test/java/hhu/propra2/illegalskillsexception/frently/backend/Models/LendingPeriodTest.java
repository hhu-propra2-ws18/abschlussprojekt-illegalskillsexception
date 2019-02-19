package hhu.propra2.illegalskillsexception.frently.backend.Models;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class LendingPeriodTest {

    private LocalDate date;

    @Before
    public void setup() {
        date = LocalDate.of(2019, 12, 24);
    }

    @Test
    public void lengthWithSameStartAndEndDateIsZero() {
        LendingPeriod lendingPeriod = new LendingPeriod(date, date);
        assertEquals(0L, lendingPeriod.getLengthInDays());
    }

    @Test
    public void lengthWithEndDateTomorrowIsOne() {
        LendingPeriod lendingPeriod = new LendingPeriod(date, date.plusDays(1));
        assertEquals(1L, lendingPeriod.getLengthInDays());
    }

    @Test
    public void lengthWithEndDateAWeekLaterIsSeven() {
        LendingPeriod lendingPeriod = new LendingPeriod(date, date.plusWeeks(1));
        assertEquals(7L, lendingPeriod.getLengthInDays());
    }
}