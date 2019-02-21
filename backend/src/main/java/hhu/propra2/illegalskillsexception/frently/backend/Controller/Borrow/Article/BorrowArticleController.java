package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IBorrowArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
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
        try {
            response.setData(articleService.retrieveAll());
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }

    @GetMapping(value = "/", params = "ofUser")
    public FrentlyResponse retrieveAllOffersOfUser(Long ofUser) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            response.setData(articleService.retrieveAllOfOwner(ofUser));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }

}
