package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.DTOs.AcceptReturnedItemRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.ArticleNotReturnedException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.NoSuchTransactionException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.IService.ILendTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class LendTransactionControllerTest {

    @InjectMocks
    private LendTransactionController lendTransactionController;
    private MockMvc mockMvc;
    @MockBean
    private ILendTransactionService mockLendTransactionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(lendTransactionController).build();
    }

    @Test
    public void retrieveAllOfUsersSuccessfully() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1);
        List<Transaction> transactionList = Collections.singletonList(transaction);

        when(mockLendTransactionService.retrieveAllOfCurrentUser(any())).thenReturn(transactionList);

        this.mockMvc.perform(get("/lend/transaction/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].inquiry").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].returnDate").value(IsNull.nullValue()));


        verify(mockLendTransactionService, times(1)).retrieveAllOfCurrentUser(any());
    }

    @Test
    public void retrieveAllOfUsersFails() throws Exception {
        when(mockLendTransactionService.retrieveAllOfCurrentUser(any())).thenThrow(Exception.class);

        this.mockMvc.perform(get("/lend/transaction/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.errorType").value("MISC"))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));


        verify(mockLendTransactionService, times(1)).retrieveAllOfCurrentUser(any());
    }

    @Test
    public void updateTransactionSuccessfully() throws Exception {
        AcceptReturnedItemRequestDTO acceptReturnedItemRequestDTO = new AcceptReturnedItemRequestDTO();
        acceptReturnedItemRequestDTO.setFaulty(true);
        acceptReturnedItemRequestDTO.setTransactionId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(acceptReturnedItemRequestDTO);

        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setStatus(Transaction.Status.CONFLICT);

        when(mockLendTransactionService.updateTransaction(any())).thenReturn(transaction);

        this.mockMvc.perform(post("/lend/transaction/update").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data.status").value("CONFLICT"));


        verify(mockLendTransactionService, times(1)).updateTransaction(any());
    }

    @Test
    public void updateTransactionFailsItemWasNotReturned() throws Exception {
        AcceptReturnedItemRequestDTO acceptReturnedItemRequestDTO = new AcceptReturnedItemRequestDTO();
        acceptReturnedItemRequestDTO.setFaulty(true);
        acceptReturnedItemRequestDTO.setTransactionId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(acceptReturnedItemRequestDTO);


        when(mockLendTransactionService.updateTransaction(any())).thenThrow(new ArticleNotReturnedException());

        this.mockMvc.perform(post("/lend/transaction/update").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.errorType").value("ARTICLE_MUST_BE_RETURNED"))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));

        verify(mockLendTransactionService, times(1)).updateTransaction(any());
    }

    @Test
    public void updateTransactionFailsTransactionNotFound() throws Exception {
        AcceptReturnedItemRequestDTO acceptReturnedItemRequestDTO = new AcceptReturnedItemRequestDTO();
        acceptReturnedItemRequestDTO.setFaulty(true);
        acceptReturnedItemRequestDTO.setTransactionId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(acceptReturnedItemRequestDTO);

        when(mockLendTransactionService.updateTransaction(any())).thenThrow(new NoSuchTransactionException());

        this.mockMvc.perform(post("/lend/transaction/update").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error.errorType").value("NO_SUCH_TRANSACTION"))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));

        verify(mockLendTransactionService, times(1)).updateTransaction(any());
    }
}