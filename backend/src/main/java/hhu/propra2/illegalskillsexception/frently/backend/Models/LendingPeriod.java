package hhu.propra2.illegalskillsexception.frently.backend.Models;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@Embeddable
public class LendingPeriod {
    private LocalDate startLend;
    private LocalDate endLend;

    public LendingPeriod(LocalDate startLend, LocalDate endLend) {
        if(startLend.isBefore(endLend)) {
            this.startLend = startLend;
            this.endLend = endLend;
        }
        else {
            this.startLend = endLend;
            this.endLend = startLend;
        }
    }

    public long getLengthInDays() {
        return startLend.until(endLend).getDays();
    }
}
