package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Article;

import hhu.propra2.illegalskillsexception.frently.backend.Borrow.Article.Services.IBorrowArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/borrow/article")
public class BorrowArticleController {

    private final IBorrowArticleService articleService;

    @GetMapping("/")
    public FrentlyResponse retrieveAllOffers(Long ofUser) {
        FrentlyResponse response = new FrentlyResponse();

        if (ofUser == null) {
            response.setData(articleService.retrieveAll());
        } else {
            response.setData(articleService.retrieveAllOfOwner(ofUser));
        }

        return response;
    }


}
