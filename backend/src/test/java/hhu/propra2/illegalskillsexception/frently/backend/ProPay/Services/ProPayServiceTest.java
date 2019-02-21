package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services;

import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IMoneyTransferService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.Reservation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ProPayServiceTest {

    private ProPayService proPayService;
    private List<Reservation> twoReservations;
    private List<Reservation> noReservation;

    @Before
    public void setup() {

        IMoneyTransferService iMoneyTransferService = mock(MoneyTransferService.class);
        proPayService = new ProPayService(iMoneyTransferService, "");
        Reservation reservationOne = new Reservation();
        Reservation reservationTwo = new Reservation();
        twoReservations = new ArrayList<>();
        reservationOne.setAmount(500);
        reservationTwo.setAmount(200);
        twoReservations.add(reservationOne);
        twoReservations.add(reservationTwo);

        noReservation = new ArrayList<>();
    }

    @Test
    public void hasEnoughMoneyNotEnough() {
        boolean hasHe = proPayService.amountGreaterThanReservation(twoReservations, 5000, 700);
        assertFalse(hasHe);
    }

    @Test
    public void hasEnoughMoneyEnough() {
        boolean hasHe = proPayService.amountGreaterThanReservation(twoReservations, 20, 30000);
        assertTrue(hasHe);
    }

    @Test
    public void hasEnoughMoneyNoReservations() {
        boolean hasHe = proPayService.amountGreaterThanReservation(noReservation, 20, 30000);
        assertTrue(hasHe);
    }
}