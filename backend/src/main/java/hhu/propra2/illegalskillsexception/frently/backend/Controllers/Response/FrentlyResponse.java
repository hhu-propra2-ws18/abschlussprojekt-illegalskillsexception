package hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response;

import lombok.Data;

@Data
public class FrentlyResponse {
    private FrentlyError error;
    private FrentlyData data;

    public FrentlyResponse (FrentlyError inputError, FrentlyData inputData) {
        this.error = inputError;
        this.data = inputData;
    }
}
