package hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.IServices.IBuyService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IBuyArticleRepository;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class BuyService implements IBuyService {

    private final IBuyArticleRepository buyArticleRepository;
    private final IProPayService proPayService;


    @Override
    public List<BuyArticle> getAllBuyableArticles() {
        return buyArticleRepository.findAll();
    }

    @Override
    public void buyItem(Long sellArticleID, ApplicationUser buyer) throws NoSuchBuyArticleException, ProPayConnectionException, InsuffientFundsException, UserNotFoundException { //TODO catch exceptions locally
        BuyArticle article = buyArticleRepository.findById(sellArticleID).orElseThrow(NoSuchBuyArticleException::new);

        ApplicationUser owner = article.getOwner();

        proPayService.transferMoney(buyer.getUsername(), owner.getUsername(), article.getPrice());

        buyArticleRepository.delete(article);
    }


}
