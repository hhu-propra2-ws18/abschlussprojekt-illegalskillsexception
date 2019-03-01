package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs.BuyArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IBuyArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SellServiceTest {

    private IApplicationUserService userService = mock(IApplicationUserService.class);
    private IBuyArticleRepository buyArticleRepository = mock(IBuyArticleRepository.class);
    private Authentication auth = mock(Authentication.class);
    private BuyArticle buyArticle = new BuyArticle();
    private SellService sellService;
    private ApplicationUser user = new ApplicationUser();

    @Before
    public void setup() {
        when(buyArticleRepository.save(any())).thenReturn(null);
        when(userService.getCurrentUser(any())).thenReturn(user);
        sellService = new SellService(userService, buyArticleRepository);
    }

    @Test
    public void createArticle() throws Exception {

        assertNull(buyArticle.getOwner());
        sellService.createArticle(buyArticle, auth);
        assertNotNull(buyArticle.getOwner());
    }

    @Test
    public void successfulArticleUpdate() throws Exception {
        when(buyArticleRepository.findById(any())).thenReturn(Optional.of(buyArticle));
        BuyArticleUpdate updateArticle = new BuyArticleUpdate();
        updateArticle.setDescription("new description");
        updateArticle.setLocation("buxdehude");
        updateArticle.setTitle("auto");
        updateArticle.setPrice(50.0);
        sellService.updateArticle(updateArticle);

        assertEquals("auto", buyArticle.getTitle());
        assertEquals("buxdehude", buyArticle.getLocation());
        assertEquals(50.0, buyArticle.getPrice().doubleValue(), 0.00001);
        assertEquals("new description", buyArticle.getDescription());

    }

    @Test(expected = Exception.class)
    public void unsuccessfulArticleUpdate() throws Exception {
        when(buyArticleRepository.findById(any())).thenThrow(new NoSuchBuyArticleException());
        BuyArticleUpdate updateArticle = new BuyArticleUpdate();
        updateArticle.setBuyArticleId(1L);
        sellService.updateArticle(updateArticle);
    }
}