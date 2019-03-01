package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs.BuyArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.IServices.ISellService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IBuyArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SellService implements ISellService {

    private final IApplicationUserService userService;
    private final IBuyArticleRepository buyArticleRepository;

    @Override
    public List<BuyArticle> getAllArticlesOfCurrentUser(Authentication auth) throws Exception {
        ApplicationUser user = userService.getCurrentUser(auth);
        return buyArticleRepository.findAllByOwner_Username(user.getUsername());
    }

    @Override
    public BuyArticle createArticle(BuyArticle buyArticle, Authentication auth) throws Exception {
        ApplicationUser user = userService.getCurrentUser(auth);
        buyArticle.setOwner(user);
        buyArticleRepository.save(buyArticle);
        return buyArticle;
    }

    @Override
    public BuyArticle updateArticle(BuyArticleUpdate update) throws NoSuchBuyArticleException {
        BuyArticle article = buyArticleRepository.findById(update.getBuyArticleId()).orElseThrow(NoSuchBuyArticleException::new);
        article.setPrice(update.getPrice());
        article.setTitle(update.getTitle());
        article.setDescription(update.getDescription());
        article.setLocation(update.getLocation());

        buyArticleRepository.save(article);

        return article;
    }

}
