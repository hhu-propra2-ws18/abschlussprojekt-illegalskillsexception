package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class ArticleAvailabilityDTO {

    public List<String> blockedTimespans;

}
