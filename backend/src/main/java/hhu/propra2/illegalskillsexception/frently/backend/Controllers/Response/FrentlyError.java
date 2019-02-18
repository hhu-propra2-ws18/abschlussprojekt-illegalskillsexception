package hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FrentlyError {
    private String errorMessage;
    private FrentlyErrorType errorType;
}