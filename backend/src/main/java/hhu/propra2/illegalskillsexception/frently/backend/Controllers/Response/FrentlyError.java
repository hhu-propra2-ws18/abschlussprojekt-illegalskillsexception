package hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response;

import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.FrentlyException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FrentlyError {
    private String errorMessage;
    private FrentlyErrorType errorType;

    public FrentlyError(FrentlyException e) {
        this.errorMessage = e.getMessage();
        this.errorType = e.getType();
    }

    public FrentlyError(Exception e) {
        this.errorMessage = e.getMessage();
        this.errorType = FrentlyErrorType.MISC;
    }
}