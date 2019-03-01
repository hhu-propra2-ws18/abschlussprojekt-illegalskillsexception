package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.DTOs.ArticleAvailabilityRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IArticleAvailabilityService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IBorrowArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/borrow/article")
public class BorrowArticleController {

    private IArticleAvailabilityService availabilityService;
    private IBorrowArticleService articleService;

    @GetMapping(value = "/")
    public FrentlyResponse retrieveAllOffersButOwn(Authentication auth) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            response.setData(articleService.retrieveAllButOwn(auth));
        } catch (Exception e) {
            response.setError(new FrentlyError(e));
        }
        return response;
    }


    @PostMapping(value = "/")
    public FrentlyResponse retrieveAvailability(@RequestBody ArticleAvailabilityRequestDTO dto) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            response.setData(availabilityService.getArticleAvailabilityDTP(dto.getArticleId()));
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
