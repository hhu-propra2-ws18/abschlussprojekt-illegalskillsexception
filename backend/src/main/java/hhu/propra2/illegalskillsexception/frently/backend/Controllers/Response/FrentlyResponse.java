package hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response;

import lombok.Data;

import java.util.List;

@Data
public class FrentlyResponse {
    private FrentlyError error;
    private List<FrentlyData> frentlyDataList;

    public <T> FrentlyResponse(FrentlyError inputError, List<FrentlyData> inputData) {
        this.error = inputError;
        this.frentlyDataList = inputData;
    }
}
