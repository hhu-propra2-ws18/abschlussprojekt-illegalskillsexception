package hhu.propra2.illegalskillsexception.frently.backend.Models;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
class LendingPeriod {
    private LocalDateTime startLend;
    private LocalDateTime endLend;
}
