package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs.BuyArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.IServices.ISellService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/sell")
public class SellController {

    private final ISellService sellService;

    @GetMapping("/")
    public FrentlyResponse getAllItemsOfUser(Authentication auth) {
        FrentlyResponse response = new FrentlyResponse();

        try {
            response.setData(sellService.getAllArticlesOfCurrentUser(auth));
        } catch (Exception exc) {
            response.setError(new FrentlyError(exc));
        }
        return response;
    }

    @PostMapping("/create")
    public FrentlyResponse createSellItem(Authentication auth, @RequestBody BuyArticle buyArticle) {

        FrentlyResponse response = new FrentlyResponse();

        try {
            final BuyArticle changedBuyArticle = sellService.createArticle(buyArticle, auth);
            response.setData(changedBuyArticle);
        } catch (Exception exc) {
            response.setError(new FrentlyError(exc));
        }
        return response;
    }

    @PostMapping("/update")
    public FrentlyResponse updateBuyItem(@RequestBody BuyArticleUpdate update) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            final BuyArticle changedBuyArticle = sellService.updateArticle(update);
            response.setData(changedBuyArticle);
        } catch (Exception exc) {
            response.setError(new FrentlyError(exc));
        }
        return response;
    }

}