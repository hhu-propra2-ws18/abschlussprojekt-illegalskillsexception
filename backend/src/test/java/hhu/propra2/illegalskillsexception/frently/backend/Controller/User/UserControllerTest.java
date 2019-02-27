package hhu.propra2.illegalskillsexception.frently.backend.Controller.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Security.ApplicationUserDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserDetailService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IUserTransactionService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserControllerTest {

    @InjectMocks
    UserController projectScsController;
    private MockMvc mockMvc;
    @Mock
    private IApplicationUserService mockUserService;
    @Mock
    private IUserDetailService mockUserDetailService;
    @Mock
    private IUserTransactionService mockTransactionService;
    @Mock
    private IProPayService mockProPayService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(projectScsController).build();
    }

    @Test
    public void signHannesUpSuccesfully() throws Exception {
        ApplicationUserDTO applicationUserDTO = new ApplicationUserDTO();
        applicationUserDTO.setUsername("Hannes");
        applicationUserDTO.setPassword("123");
        applicationUserDTO.setEmail("HannesSupercool@yahoo.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(applicationUserDTO);

        this.mockMvc.perform(post("/user/sign-up").contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].email").value("HannesSupercool@yahoo.com"))
                .andExpect(jsonPath("$.data[0].username").value("Hannes"))
                .andExpect(jsonPath("$.data[0].roles").value(IsNull.nullValue()));


        verify(mockUserService).encryptPassword(any());
        verify(mockUserService, times(1)).createUser(any());
        verify(mockProPayService, times(1)).createAccount(any(), eq(0.0));
    }
}