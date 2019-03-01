package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.DTOs.ReturnItemRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Transaction.IServices.IBorrowTransactionService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class BorrowTransactionControllerTest {

    @InjectMocks
    BorrowTransactionController borrowTransactionController;

    @MockBean
    IBorrowTransactionService transactionService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(borrowTransactionController).build();
    }

    @Test
    public void retrieveAllOfUserTest() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(10);

        when(transactionService.retrieveAllOfCurrentUser(any()))
                .thenReturn(Collections.singletonList(transaction));

        this.mockMvc.perform(get("/borrow/transaction/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data[0].id").value("10"))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()));
    }

    @Test
    public void retrieveAllOfUserFailed() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(10);

        when(transactionService.retrieveAllOfCurrentUser(any()))
                .thenThrow(new Exception());

        this.mockMvc.perform(get("/borrow/transaction/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.error.errorType").value("MISC"));
    }

    @Test
    public void returnItemTest() throws Exception {
        ReturnItemRequestDTO returnItemRequestDTO = new ReturnItemRequestDTO();
        returnItemRequestDTO.setTransactionId(10);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(returnItemRequestDTO);

        Transaction transaction = new Transaction();
        transaction.setId(10);
        transaction.setStatus(Transaction.Status.CLOSED);

        when(transactionService.updateTransaction(any())).thenReturn(transaction);

        this.mockMvc.perform(get("/borrow/transaction/return/").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data.status").value("CLOSED"))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()));
    }
}
