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

    @GetMapping(value = "/")
    public FrentlyResponse retrieveAllOffers() {
        FrentlyResponse response = new FrentlyResponse();
        response.setData(articleService.retrieveAll());
        return response;
    }

    @GetMapping(value = "/", params = "ofUser")
    public FrentlyResponse retrieveAllOffersOfUser(Long ofUser) {
        FrentlyResponse response = new FrentlyResponse();
        response.setData(articleService.retrieveAllOfOwner(ofUser));
        return response;
    }

}