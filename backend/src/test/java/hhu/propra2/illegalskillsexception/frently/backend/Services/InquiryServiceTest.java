package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.*;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IInquiryRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InquiryServiceTest {

    private ArrayList<Inquiry> inquiryList;
    private IInquiryRepository IInquiryRepository;
    private InquiryService inquiryService;
    private TransactionService transactionService;

    @Before
    public void setup() {
        ApplicationUser user1 = new ApplicationUser();
        ApplicationUser user2 = new ApplicationUser();
        ApplicationUser user3 = new ApplicationUser();
        user1.setId(0L);
        user2.setId(1L);
        user3.setId(2L);

        Inquiry inquiry0 = mock(Inquiry.class);
        when(inquiry0.getId()).thenReturn(0L);
        when(inquiry0.getDuration()).thenReturn(new LendingPeriod(LocalDate.of(2019, 4, 23), LocalDate.of(2019, 4, 23)));
        when(inquiry0.getTimestamp()).thenReturn(LocalDateTime.of(2019, 4, 23, 22, 12, 43));
        when(inquiry0.getUpdated()).thenReturn(LocalDateTime.of(2019, 4, 23, 22, 12, 43));
        when(inquiry0.getBorrower()).thenReturn(user1);
        when(inquiry0.getLender()).thenReturn(user2);

        Inquiry inquiry1 = mock(Inquiry.class);
        when(inquiry1.getId()).thenReturn(1L);
        when(inquiry1.getDuration()).thenReturn(new LendingPeriod(LocalDate.of(2019, 4, 25), LocalDate.of(2019, 4, 28)));
        when(inquiry1.getTimestamp()).thenReturn(LocalDateTime.of(2019, 4, 23, 22, 12, 47));
        when(inquiry1.getUpdated()).thenReturn(LocalDateTime.of(2019, 4, 23, 22, 12, 47));
        when(inquiry1.getBorrower()).thenReturn(user3);
        when(inquiry1.getLender()).thenReturn(user1);

        inquiryList = new ArrayList<>();
        inquiryList.addAll(Arrays.asList(inquiry0, inquiry1));

        List<Inquiry> list1 = new ArrayList<>();
        List<Inquiry> list2 = new ArrayList<>();
        list1.add(new Inquiry());
        list2.add(new Inquiry());

        IInquiryRepository = mock(IInquiryRepository.class);
        when(IInquiryRepository.findAll()).thenReturn(inquiryList);
        when(IInquiryRepository.findById(0L)).thenReturn(Optional.of(inquiry0));
        when(IInquiryRepository.findById(1L)).thenReturn(null);
        when(IInquiryRepository.existsById(0L)).thenReturn(true);
        when(IInquiryRepository.existsById(1L)).thenReturn(false);
        when(IInquiryRepository.findAllByBorrower_Id(0L)).thenReturn(list1);
        when(IInquiryRepository.findAllByLender_Id(0L)).thenReturn(list2);

        transactionService = mock(TransactionService.class);
        inquiryService = new InquiryService(IInquiryRepository, transactionService);
    }

    @Test
    public void updateInquiryWhichExists() {
        Inquiry tmpInquiry = inquiryList.get(0);
        inquiryService.updateInquiry(tmpInquiry);
        verify(IInquiryRepository).save(tmpInquiry);
    }

    @Test
    public void updateInquiryWhichNotExists() {
        Inquiry tmpInquiry = inquiryList.get(1);
        inquiryService.updateInquiry(tmpInquiry);
        verify(IInquiryRepository, never()).save(tmpInquiry);
    }

    @Test
    public void getInquiryWhichExists() {
        Inquiry expectedInquiry = inquiryList.get(0);

        Inquiry inquiry = inquiryService.getInquiry(0L);

        assertEquals(expectedInquiry.getId(), inquiry.getId());
        assertNotNull(inquiry);
    }

    @Test
    public void getInquiryWhichNotExists() {
        Inquiry inquiry = inquiryService.getInquiry(1L);
        assertNull(inquiry);
    }

    @Test
    public void getAllInquiries() {
        List<Inquiry> list = inquiryService.getAllInquiries(0L);
        assertEquals(2,list.size());
    }

    @Test
    public void calculatePrize() {
        LendingPeriod lendingPeriod = mock(LendingPeriod.class);
        when(lendingPeriod.getLengthInDays()).thenReturn(5L);

        Article article = new Article();
        article.setDailyRate(10.);

        Inquiry inquiry = new Inquiry();
        inquiry.setArticle(article);
        inquiry.setDuration(lendingPeriod);

        // 5 * 10 + 25 = 75
        assertEquals(50., inquiryService.calculateAccumulatedDailyRate(inquiry), 0.0000001);
    }

    @Test
    public void hasExactlyEnoughMoney() {
        ProPayAccount account = new ProPayAccount();
        account.setAmount(25.);
        assertTrue(inquiryService.hasEnoughMoney(account, 25.));
    }

    @Test
    public void hasEnoughMoney() {
        ProPayAccount account = new ProPayAccount();
        account.setAmount(25.);
        assertFalse(inquiryService.hasEnoughMoney(account, 30.));
    }

    @Test
    public void hasNotEnoughMoney() {
        ProPayAccount account = new ProPayAccount();
        account.setAmount(25.);
        assertTrue(inquiryService.hasEnoughMoney(account, 24.99));
    }
}