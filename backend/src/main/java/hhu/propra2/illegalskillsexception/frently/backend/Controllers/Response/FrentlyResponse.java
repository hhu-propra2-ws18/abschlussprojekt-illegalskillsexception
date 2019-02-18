package hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrentlyResponse {
    private FrentlyError error;
    private List<FrentlyData> frentlyDataList;

}
