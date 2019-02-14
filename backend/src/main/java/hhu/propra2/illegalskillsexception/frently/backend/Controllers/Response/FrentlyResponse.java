package hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FrentlyResponse {
    private FrentlyError error;
    private FrentlyData data;
}
