package hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.DTOs.BuyArticleIDRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.IServices.IBuyService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class BuyControllerTest {
    @InjectMocks
    private BuyController buyController;
    @MockBean
    private IApplicationUserService userService;
    @MockBean
    private IBuyService buyService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(buyController).build();
    }

    @Test
    public void listBuyableItems() throws Exception {
        BuyArticle article = new BuyArticle();
        article.setDescription("Test article");
        article.setLocation("HHU");
        article.setOwner(new ApplicationUser());
        article.setPrice(1000.0);
        article.setTitle("Test");

        when(buyService.getAllBuyableArticlesButOwn(any()))
                .thenReturn(Collections.singletonList(article));

        this.mockMvc.perform(get("/buy/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].description").value("Test article"))
                .andExpect(jsonPath("$.data[0].price").value(1000.0))
                .andExpect(jsonPath("$.data.[0].title").value("Test"))
                .andExpect(jsonPath("$.data[0].location").value("HHU"));
    }

    @Test
    public void listBuyableItemsWithError() throws Exception {
        when(buyService.getAllBuyableArticlesButOwn(any())).thenThrow(Exception.class);

        this.mockMvc.perform(get("/buy/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.error.errorType").value("MISC"));
    }

    @Test
    public void buyItemSuccessful() throws Exception {
        BuyArticleIDRequestDTO requestDTO = new BuyArticleIDRequestDTO();
        requestDTO.setBuyArticleId(1);

        ApplicationUser buyer = new ApplicationUser();
        when(userService.getCurrentUser(any())).thenReturn(buyer);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        this.mockMvc.perform(post("/buy/buyItem").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));
    }

    @Test   //This test is a representative for all FrentlyExceptions
    public void buyItemAndFailWithNoSuchBuyArticleException() throws Exception {
        BuyArticleIDRequestDTO requestDTO = new BuyArticleIDRequestDTO();
        requestDTO.setBuyArticleId(1);

        ApplicationUser buyer = new ApplicationUser();
        when(userService.getCurrentUser(any())).thenReturn(buyer);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        doThrow(new NoSuchBuyArticleException()).when(buyService).buyItem(any(), any());

        this.mockMvc.perform(post("/buy/buyItem").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.errorType").value("NO_SUCH_SELL_ARTICLE"))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));
    }
}
