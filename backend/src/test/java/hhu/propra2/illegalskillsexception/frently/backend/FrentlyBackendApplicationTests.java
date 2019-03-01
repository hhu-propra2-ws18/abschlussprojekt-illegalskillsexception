package hhu.propra2.illegalskillsexception.frently.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Security.ApplicationUserDTO;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IMoneyTransferService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services.MoneyTransferService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services.ProPayService;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@AutoConfigureMockMvc
public class FrentlyBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private WireMockServer wireMockServer;
    private ProPayService proPayService;

    @Before
    public void setup() {
        wireMockServer = new WireMockServer(8888);
        IMoneyTransferService iMoneyTransferService = mock(MoneyTransferService.class);
        proPayService = new ProPayService(iMoneyTransferService, "http://localhost:8888/");
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @After
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void userCanRegister() throws Exception {
        ApplicationUserDTO user = new ApplicationUserDTO();
        user.setUsername("user");
        user.setEmail("user@frently.com");
        user.setPassword("password");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user);

        stubFor(WireMock.post(urlPathMatching("/account/user"))
                .withQueryParam("amount", equalTo("0.0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"user\",\n" +
                                "  \"amount\": 0,\n" +
                                "  \"reservations\": []\n" +
                                "}")));

        this.mockMvc.perform(post("/user/sign-up")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.data[0].email").value("user@frently.com"))
                .andExpect(jsonPath("$.data[0].username").value("user"));
    }
}

