package hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IBuyService {

    List<BuyArticle> getAllBuyableArticlesButOwn(Authentication auth) throws Exception;

    void buyItem(Long sellArticleID, ApplicationUser buyer) throws NoSuchBuyArticleException, ProPayConnectionException, InsuffientFundsException, UserNotFoundException;

}
