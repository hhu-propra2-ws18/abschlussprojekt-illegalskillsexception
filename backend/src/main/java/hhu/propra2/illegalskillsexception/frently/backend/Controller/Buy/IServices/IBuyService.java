package hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;

import java.util.List;

public interface IBuyService {

    List<BuyArticle> getAllBuyableArticles();

    Long buyItem(Long sellArticleID, ApplicationUser buyer) throws NoSuchBuyArticleException, ProPayConnectionException, InsuffientFundsException;

}
