package hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy;


import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.DTOs.BuyArticleIDRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.IServices.IBuyService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/buy")
public class BuyController {
    private IApplicationUserService userService;
    private IBuyService buyService;

    @GetMapping("/")
    public FrentlyResponse getAllButOwn(Authentication auth) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            response.setData(buyService.getAllBuyableArticlesButOwn(auth));
        } catch (Exception exc) {
            response.setError(new FrentlyError(exc));
        }
        return response;
    }

    @PostMapping("/buyItem")
    public FrentlyResponse buyItem(Authentication auth, @RequestBody BuyArticleIDRequestDTO dto) {
        FrentlyResponse response = new FrentlyResponse();
        ApplicationUser buyer = userService.getCurrentUser(auth);
        try {
            buyService.buyItem(dto.getBuyArticleId(), buyer);
        } catch (FrentlyException frexc) {
            response.setError(new FrentlyError(frexc));
        } catch (Exception exc) {
            response.setError(new FrentlyError(exc));
        }
        return response;
    }
}