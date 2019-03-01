package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Buy.Exceptions.NoSuchBuyArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs.BuyArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.IServices.ISellService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class SellControllerTest {

    @InjectMocks
    private SellController sellController;
    private MockMvc mockMvc;
    @MockBean
    private ISellService mockSellService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(sellController).build();
    }

    @Test
    public void getAllHannesItemsSuccessfully() throws Exception {
        ApplicationUser hannes = new ApplicationUser();
        hannes.setPassword("123");
        hannes.setEmail("HannesSupercool@yahoo.com");
        hannes.setUsername("Hannes");
        hannes.setRoles(null);
        hannes.setId(1);
        hannes.setTimestamp(LocalDateTime.now());
        hannes.setUpdated(LocalDateTime.now());

        BuyArticle buyArticle = new BuyArticle();
        buyArticle.setTitle("Hannes Toast");
        buyArticle.setDescription("Hannes beloved Toast");
        buyArticle.setOwner(hannes);
        buyArticle.setLocation("uber eck");
        buyArticle.setPrice(500.0);
        buyArticle.setId(5);
        buyArticle.setTimestamp(LocalDateTime.now());
        buyArticle.setUpdated(LocalDateTime.now());

        ArrayList<BuyArticle> articleList = new ArrayList<>();
        articleList.add(buyArticle);

        when(mockSellService.getAllArticlesOfCurrentUser(any())).thenReturn(articleList);

        this.mockMvc.perform(get("/sell/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].title").value("Hannes Toast"))
                .andExpect(jsonPath("$.data[0].owner.email").value("HannesSupercool@yahoo.com"))
                .andExpect(jsonPath("$.data[0].owner.username").value("Hannes"))
                .andExpect(jsonPath("$.data[0].price").value("500.0"))
                .andExpect(jsonPath("$.data[0].description").value("Hannes beloved Toast"))
                .andExpect(jsonPath("$.data[0].location").value("uber eck"));


        verify(mockSellService, times(1)).getAllArticlesOfCurrentUser(any());
    }

    @Test
    public void getAllHannesItemsFails() throws Exception {
        when(mockSellService.getAllArticlesOfCurrentUser(any())).thenThrow(Exception.class);
        this.mockMvc.perform(get("/sell/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.errorType").value("MISC"))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));


        verify(mockSellService, times(1)).getAllArticlesOfCurrentUser(any());
    }


    @Test
    public void createSellArticleSuccesfully() throws Exception {
        ApplicationUser hannes = new ApplicationUser();
        hannes.setPassword("123");
        hannes.setEmail("HannesSupercool@yahoo.com");
        hannes.setUsername("Hannes");
        hannes.setRoles(null);
        hannes.setId(1);
        hannes.setTimestamp(LocalDateTime.now());
        hannes.setUpdated(LocalDateTime.now());
        BuyArticle buyArticle = new BuyArticle();
        buyArticle.setTitle("Hannes Toast");
        buyArticle.setDescription("Hannes beloved Toast");
        buyArticle.setOwner(hannes);
        buyArticle.setLocation("uber eck");
        buyArticle.setPrice(500.0);
        buyArticle.setId(5);
        buyArticle.setTimestamp(LocalDateTime.now());
        buyArticle.setUpdated(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(buyArticle);

        when(mockSellService.createArticle(any(), any())).thenReturn(buyArticle);

        this.mockMvc.perform(post("/sell/create").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data.title").value("Hannes Toast"))
                .andExpect(jsonPath("$.data.owner.email").value("HannesSupercool@yahoo.com"))
                .andExpect(jsonPath("$.data.owner.username").value("Hannes"))
                .andExpect(jsonPath("$.data.price").value("500.0"))
                .andExpect(jsonPath("$.data.description").value("Hannes beloved Toast"))
                .andExpect(jsonPath("$.data.location").value("uber eck"));

        verify(mockSellService, times(1)).createArticle(any(), any());
    }

    @Test
    public void createSellArticleFails() throws Exception {
        ApplicationUser hannes = new ApplicationUser();
        hannes.setPassword("123");
        hannes.setEmail("HannesSupercool@yahoo.com");
        hannes.setUsername("Hannes");
        hannes.setRoles(null);
        hannes.setId(1);
        hannes.setTimestamp(LocalDateTime.now());
        hannes.setUpdated(LocalDateTime.now());
        BuyArticle buyArticle = new BuyArticle();
        buyArticle.setTitle("Hannes Toast");
        buyArticle.setDescription("Hannes beloved Toast");
        buyArticle.setOwner(hannes);
        buyArticle.setLocation("uber eck");
        buyArticle.setPrice(500.0);
        buyArticle.setId(5);
        buyArticle.setTimestamp(LocalDateTime.now());
        buyArticle.setUpdated(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(buyArticle);

        when(mockSellService.createArticle(any(), any())).thenThrow(Exception.class);

        this.mockMvc.perform(post("/sell/create").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.errorType").value("MISC"))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));

        verify(mockSellService, times(1)).createArticle(any(), any());
    }


    @Test
    public void updateBuyArticleSuccessfully() throws Exception {
        BuyArticleUpdate buyArticleUpdate = new BuyArticleUpdate();
        buyArticleUpdate.setBuyArticleId(1);
        buyArticleUpdate.setDescription("Hannes beloved Roast");
        buyArticleUpdate.setLocation("uber eck");
        buyArticleUpdate.setPrice(500.0);
        buyArticleUpdate.setTitle("Hannes Roast");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(buyArticleUpdate);

        ApplicationUser hannes = new ApplicationUser();
        hannes.setPassword("123");
        hannes.setEmail("HannesSupercool@yahoo.com");
        hannes.setUsername("Hannes");
        hannes.setRoles(null);
        hannes.setId(1);
        hannes.setTimestamp(LocalDateTime.now());
        hannes.setUpdated(LocalDateTime.now());
        BuyArticle buyArticle = new BuyArticle();
        buyArticle.setTitle("Hannes Roast");
        buyArticle.setDescription("Hannes beloved Roast");
        buyArticle.setOwner(hannes);
        buyArticle.setLocation("uber eck");
        buyArticle.setPrice(500.0);
        buyArticle.setId(5);
        buyArticle.setTimestamp(LocalDateTime.now());
        buyArticle.setUpdated(LocalDateTime.now());

        when(mockSellService.updateArticle(any())).thenReturn(buyArticle);

        this.mockMvc.perform(post("/sell/update").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data.title").value("Hannes Roast"))
                .andExpect(jsonPath("$.data.owner.email").value("HannesSupercool@yahoo.com"))
                .andExpect(jsonPath("$.data.owner.username").value("Hannes"))
                .andExpect(jsonPath("$.data.price").value("500.0"))
                .andExpect(jsonPath("$.data.description").value("Hannes beloved Roast"))
                .andExpect(jsonPath("$.data.location").value("uber eck"));

        verify(mockSellService, times(1)).updateArticle(any());
    }

    @Test
    public void updateBuyArticleFails() throws Exception {
        BuyArticleUpdate buyArticleUpdate = new BuyArticleUpdate();
        buyArticleUpdate.setBuyArticleId(1);
        buyArticleUpdate.setDescription("Hannes beloved Roast");
        buyArticleUpdate.setLocation("uber eck");
        buyArticleUpdate.setPrice(500.0);
        buyArticleUpdate.setTitle("Hannes Roast");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(buyArticleUpdate);

        when(mockSellService.updateArticle(any())).thenThrow(NoSuchBuyArticleException.class);

        this.mockMvc.perform(post("/sell/update").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.errorType").value("MISC"))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));

        verify(mockSellService, times(1)).updateArticle(any());
    }
}