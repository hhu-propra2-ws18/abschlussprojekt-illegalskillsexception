package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs.BuyArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ISellService {

    List<BuyArticle> getAllArticlesOfCurrentUser(Authentication auth);

    BuyArticle createArticle(BuyArticle buyArticle, Authentication auth);

    BuyArticle updateArticle(BuyArticleUpdate update) throws NoSuchBuyArticleException;
}
