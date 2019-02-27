package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs.BuyArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;

import java.util.List;

public interface ISellService {

    List<BuyArticle> getAllArticlesOfUser(ApplicationUser user);

    BuyArticle createArticle(BuyArticle buyArticle, ApplicationUser user);

    BuyArticle updateArticle(BuyArticleUpdate update) throws NoSuchBuyArticleException;
}
