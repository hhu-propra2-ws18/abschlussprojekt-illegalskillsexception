package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.DTOs.ArticleAvailabilityDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.DTOs.ArticleAvailabilityRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IArticleAvailabilityService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IBorrowArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class BorrowArticleControllerTest {
    @InjectMocks
    BorrowArticleController articleController;
    @MockBean
    IArticleAvailabilityService availabilityService;
    @MockBean
    IBorrowArticleService articleService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    public void retrieveAllOffersButOwnTest() throws Exception {
        BorrowArticle borrowArticle = new BorrowArticle();
        borrowArticle.setId(10);
        borrowArticle.setOwner(new ApplicationUser());

        when(articleService.retrieveAllButOwn(any()))
                .thenReturn(Collections.singletonList(borrowArticle));

        this.mockMvc.perform(get("/borrow/article/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].id").value("10"));
    }

    @Test
    public void retrieveAllOffersButOwnAndFail() throws Exception {
        doThrow(new Exception()).when(articleService).retrieveAllButOwn(any());

        this.mockMvc.perform(get("/borrow/article/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.error.errorType").value("MISC"));
    }

    @Test
    public void retrieveAvailabilityTest() throws Exception {

        ArticleAvailabilityRequestDTO requestDTO = new ArticleAvailabilityRequestDTO();
        requestDTO.setArticleId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        ArticleAvailabilityDTO availabilityDTO = new ArticleAvailabilityDTO();
        availabilityDTO.setBlockedTimespans(Collections.singletonList("Test"));

        when(availabilityService.getArticleAvailabilityDTP(anyLong()))
                .thenReturn(availabilityDTO);

        this.mockMvc.perform(post("/borrow/article/").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data.blockedTimespans[0]").value("Test"))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()));
    }

    @Test
    public void retrieveAvailabilityFailed() throws Exception {

        ArticleAvailabilityRequestDTO requestDTO = new ArticleAvailabilityRequestDTO();
        requestDTO.setArticleId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        ArticleAvailabilityDTO availabilityDTO = new ArticleAvailabilityDTO();
        availabilityDTO.setBlockedTimespans(Collections.singletonList("Test"));

        when(availabilityService.getArticleAvailabilityDTP(anyLong()))
                .thenThrow(new Exception());

        this.mockMvc.perform(post("/borrow/article/").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.error.errorType").value("MISC"));
    }

    @Test
    public void retrieveAllOffersOfOwnerTest() throws Exception {
        BorrowArticle borrowArticle = new BorrowArticle();
        borrowArticle.setOwner(new ApplicationUser());
        borrowArticle.setId(10);

        when(articleService.retrieveAllOfOwner(anyLong()))
                .thenReturn(Collections.singletonList(borrowArticle));

        this.mockMvc.perform(get("/borrow/article/").contentType(APPLICATION_JSON_UTF8)
                .param("ofUser", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data[0].id").value("10"))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()));
    }

    @Test
    public void retrieveAllOffersOfOwnerFailed() throws Exception {
        when(articleService.retrieveAllOfOwner(anyLong()))
                .thenThrow(new Exception());

        this.mockMvc.perform(get("/borrow/article/").contentType(APPLICATION_JSON_UTF8)
                .param("ofUser", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.error.errorType").value("MISC"));
    }
}
