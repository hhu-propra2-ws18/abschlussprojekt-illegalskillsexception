package hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response;

import lombok.Data;

@Data
public class FrentlyError {
    private String errorMessage;
    private Integer errorCode;
}
