package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IMoneyTransferService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.ProPayAccount;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services.MoneyTransferService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services.ProPayService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Mockito.mock;

@ActiveProfiles("dev")
public class ProPayIntegrationTest {

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
    public void createHannesAccountWithNoCash() throws ProPayConnectionException {
        stubFor(post(urlPathMatching("/account/Hannes"))
                .withQueryParam("amount", equalTo("0.0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Hannes\",\n" +
                                "  \"amount\": 0,\n" +
                                "  \"reservations\": []\n" +
                                "}")));


        ProPayAccount hannesAccount = proPayService.createAccount("Hannes", 0);

        Assert.assertEquals("Hannes", hannesAccount.getAccount());
        Assert.assertEquals((Double) 0.0, hannesAccount.getAmount());
        Assert.assertEquals(0, hannesAccount.getReservations().size());
        verify(postRequestedFor(urlEqualTo("/account/Hannes?amount=0.0")));

    }

    @Test
    public void createAnnesAccountWith100Euro() throws ProPayConnectionException {
        stubFor(post(urlPathMatching("/account/Anne"))
                .withQueryParam("amount", equalTo("100.0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Anne\",\n" +
                                "  \"amount\": 100,\n" +
                                "  \"reservations\": []\n" +
                                "}")));


        ProPayAccount annesAccount = proPayService.createAccount("Anne", 100);

        Assert.assertEquals("Anne", annesAccount.getAccount());
        Assert.assertEquals((Double) 100.0, annesAccount.getAmount());
        Assert.assertEquals(0, annesAccount.getReservations().size());
        verify(postRequestedFor(urlEqualTo("/account/Anne?amount=100.0")));
    }

    @Test
    public void getAnnesAccountBalanceWhichIsZero() throws ProPayConnectionException {
        stubFor(get(urlPathMatching("/account/Anne"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Anne\",\n" +
                                "  \"amount\": 0,\n" +
                                "  \"reservations\": []\n" +
                                "}")));


        double accountBalance = proPayService.getAccountBalance("Anne");

        Assert.assertEquals(0.0, accountBalance, 0.001);
        verify(getRequestedFor(urlEqualTo("/account/Anne")));
    }

    @Test
    public void transfer100EuroBetweenMaxAndMoritz() throws ProPayConnectionException, InsuffientFundsException {
        stubFor(get(urlPathMatching("/account/Max"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Max\",\n" +
                                "  \"amount\": 200,\n" +
                                "  \"reservations\": []\n" +
                                "}")));

        stubFor(post(urlPathMatching("/account/Max/transfer/Moritz"))
                .withQueryParam("amount", equalTo("100.0"))
                .willReturn(aResponse()
                        .withStatus(200)));


        proPayService.transferMoney("Max", "Moritz", 100.0);
        verify(getRequestedFor(urlEqualTo("/account/Max")));
    }

    @Test
    public void blockDepositOf80EuroOnMoritzProPay() throws ProPayConnectionException, InsuffientFundsException {
        stubFor(get(urlPathMatching("/account/Moritz"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Moritz\",\n" +
                                "  \"amount\": 150,\n" +
                                "  \"reservations\": []\n" +
                                "}")));

        stubFor(post(urlPathMatching("/reservation/reserve/Moritz/Max"))
                .withQueryParam("amount", equalTo("80.0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"amount\": 80,\n" +
                                "  \"id\": 1\n" +
                                "}")));

        Long reservationID = proPayService.blockDeposit("Moritz", "Max", 80.0);

        Assert.assertEquals((Long) 1L, reservationID);
        verify(getRequestedFor(urlEqualTo("/account/Moritz")));
        verify(postRequestedFor(urlEqualTo("/reservation/reserve/Moritz/Max?amount=80.0")));
    }

    @Test(expected = InsuffientFundsException.class)
    public void blockDepositFailsBecauseMoritzDoesNotHaveEnoughMoney() throws ProPayConnectionException, InsuffientFundsException {
        stubFor(get(urlPathMatching("/account/Moritz"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Moritz\",\n" +
                                "  \"amount\": 50,\n" +
                                "  \"reservations\": []\n" +
                                "}")));

        stubFor(post(urlPathMatching("/reservation/reserve/Max/Moritz"))
                .withQueryParam("amount", equalTo("80.0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"amount\": 80,\n" +
                                "  \"id\": 1\n" +
                                "}")));

        proPayService.blockDeposit("Moritz", "Max", 80.0);
        verify(getRequestedFor(urlEqualTo("/account/Moritz")));
        verify(postRequestedFor(urlEqualTo("/reservation/reserve/Max/Moritz")));
    }

    @Test(expected = ProPayConnectionException.class)
    public void blockDepositFailsBecauseProPayWasNotFound() throws ProPayConnectionException, InsuffientFundsException {
        stubFor(get(urlPathMatching("/account/Moritz"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Moritz\",\n" +
                                "  \"amount\": 200,\n" +
                                "  \"reservations\": []\n" +
                                "}")));

        stubFor(post(urlPathMatching("/reservation/reserve/Moritz/Max"))
                .withQueryParam("amount", equalTo("80.0"))
                .willReturn(aResponse()
                        .withStatus(404)));

        proPayService.blockDeposit("Moritz", "Max", 80.0);
        verify(getRequestedFor(urlEqualTo("/account/Moritz")));
    }

    @Test
    public void freeDeposit80EuroFromMoritzAccount() throws ProPayConnectionException {
        stubFor(post(urlPathMatching("/reservation/release/Moritz"))
                .withQueryParam("reservationId", equalTo("1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Moritz\",\n" +
                                "  \"amount\": 200,\n" +
                                "  \"reservations\": []\n" +
                                "}")));

        Transaction transaction = new Transaction();
        transaction.setReservationId(1L);

        proPayService.freeDeposit("Moritz", transaction);

        verify(postRequestedFor(urlEqualTo("/reservation/release/Moritz?reservationId=1")));
    }


    @Test
    public void punishMoritzGiveThe100EuroDepositToMax() throws ProPayConnectionException {
        stubFor(post(urlPathMatching("/reservation/punish/Moritz"))
                .withQueryParam("reservationId", equalTo("1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\n" +
                                "  \"account\": \"Moritz\",\n" +
                                "  \"amount\": 100,\n" +
                                "  \"reservations\": []\n" +
                                "}")));

        BorrowArticle borrowArticle = new BorrowArticle();
        borrowArticle.setDeposit(100.0);

        ApplicationUser lender = new ApplicationUser();
        lender.setUsername("Max");

        Inquiry inquiry = new Inquiry();
        inquiry.setLender(lender);
        inquiry.setBorrowArticle(borrowArticle);

        Transaction transaction = new Transaction();
        transaction.setInquiry(inquiry);
        transaction.setReservationId(1L);

        proPayService.punishUser("Moritz", transaction);

        verify(postRequestedFor(urlEqualTo("/reservation/punish/Moritz?reservationId=1")));
    }
}