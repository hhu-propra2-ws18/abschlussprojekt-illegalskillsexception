package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs.BuyArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.IServices.ISellService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IBuyArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SellService implements ISellService {

    private final IBuyArticleRepository buyArticleRepository;

    @Override
    public List<BuyArticle> getAllArticlesOfUser(ApplicationUser user) {
        return buyArticleRepository.findAllByOwner_Username(user.getUsername());
    }

    @Override
    public BuyArticle createArticle(BuyArticle buyArticle, ApplicationUser user) {
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
