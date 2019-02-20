package hhu.propra2.illegalskillsexception.frently.backend.Lend.Article;

import lombok.Data;

@Data
class LendArticleUpdate {
    private Long articleId;
    private String title;
    private Double deposit;
    private String description;
    private Double dailyRate;
    private String location;
}
