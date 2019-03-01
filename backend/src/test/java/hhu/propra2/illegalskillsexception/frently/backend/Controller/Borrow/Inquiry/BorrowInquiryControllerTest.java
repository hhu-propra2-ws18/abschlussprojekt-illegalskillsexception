package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.DTOs.BorrowInquiryResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.IServices.IBorrowInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class BorrowInquiryControllerTest {

    @InjectMocks
    private BorrowInquiryController borrowInquiryController;
    private MockMvc mockMvc;
    @MockBean
    private IApplicationUserService mockApplicationUserService;
    @MockBean
    private IBorrowInquiryService mockBorrowInquiryService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(borrowInquiryController).build();
    }

    @Test
    public void retrieveAllInquiriesFromUserSuccess() throws Exception {
        ApplicationUser user = new ApplicationUser();
        user.setUsername("TestUser");
        Inquiry toResponseInquiry = new Inquiry();
        toResponseInquiry.setId(1L);
        toResponseInquiry.setStatus(Inquiry.Status.OPEN);
        toResponseInquiry.setBorrower(user);
        BorrowInquiryResponseDTO inquiry = new BorrowInquiryResponseDTO(toResponseInquiry);
        List<BorrowInquiryResponseDTO> inquiryList = Collections.singletonList(inquiry);

        when(mockApplicationUserService.getCurrentUser(any(Authentication.class))).thenReturn(user);
        when(mockBorrowInquiryService.retrieveAllUnacceptedInquiriesByUser(any())).thenReturn(inquiryList);

        this.mockMvc.perform(get("/borrow/inquiry/").contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].status").value("OPEN"))
                .andExpect(jsonPath("$.data[0].borrower.username").value("TestUser"));

        verify(mockApplicationUserService, times(1)).getCurrentUser(any());
        verify(mockBorrowInquiryService, times(1)).retrieveAllUnacceptedInquiriesByUser(any());
    }

    @Test
    public void retrieveAllInquiriesFromUserFails() throws Exception {
        ApplicationUser user = new ApplicationUser();
        user.setUsername("TestUser");
        when(mockApplicationUserService.getCurrentUser(any())).thenReturn(user);
        when(mockBorrowInquiryService.retrieveAllUnacceptedInquiriesByUser(any())).thenReturn(null);

        this.mockMvc.perform(get("/borrow/inquiry/").contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data").value(IsNull.nullValue()));

        verify(mockApplicationUserService, times(1)).getCurrentUser(any());
        verify(mockBorrowInquiryService, times(1)).retrieveAllUnacceptedInquiriesByUser(any());
    }

}