package hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IBuyArticleRepository;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuyServiceTest {

    private IBuyArticleRepository buyArticleRepository;
    private IProPayService proPayService;
    private IApplicationUserService userService;
    private BuyService buyService;
    private BuyArticle article = new BuyArticle();
    private ApplicationUser owner = new ApplicationUser();
    private ApplicationUser buyer = new ApplicationUser();

    @Before
    public void setup() {
        proPayService = mock(IProPayService.class);
        buyArticleRepository = mock(IBuyArticleRepository.class);
        userService = mock(IApplicationUserService.class);
        buyService = new BuyService(buyArticleRepository, proPayService, userService);
        owner.setUsername("owner");
        buyer.setUsername("buyer");

        when(buyArticleRepository.findById(any())).thenReturn(Optional.of(article));

    }

    @Test
    public void buyItemSuccessful() throws Exception {
        article.setId(1L);
        article.setPrice(5.0);
        article.setOwner(owner);
        buyService.buyItem(1L, buyer);
    }

    @Test(expected = Exception.class)
    public void buyItemUnsuccessful() throws Exception {
        article.setId(1L);
        article.setPrice(5.0);
        article.setOwner(owner);
        when(buyArticleRepository.findById(any())).thenThrow(new NoSuchBuyArticleException());
        buyService.buyItem(1L, buyer);
    }
}