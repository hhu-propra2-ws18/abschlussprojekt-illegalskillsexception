package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.LendingPeriod;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.InquiryRepository;
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
    private InquiryRepository inquiryRepository;
    private InquiryService inquiryService;

    @Before
    public void setup() {
        Inquiry inquiry0 = mock(Inquiry.class);
        when(inquiry0.getId()).thenReturn(0L);
        when(inquiry0.getDuration()).thenReturn(new LendingPeriod(LocalDate.of(2019, 4, 23), LocalDate.of(2019, 4, 23)));
        when(inquiry0.getTimestamp()).thenReturn(LocalDateTime.of(2019, 4, 23, 22, 12, 43));
        when(inquiry0.getUpdated()).thenReturn(LocalDateTime.of(2019, 4, 23, 22, 12, 43));

        Inquiry inquiry1 = mock(Inquiry.class);
        when(inquiry1.getId()).thenReturn(1L);
        when(inquiry1.getDuration()).thenReturn(new LendingPeriod(LocalDate.of(2019, 4, 25), LocalDate.of(2019, 4, 28)));
        when(inquiry1.getTimestamp()).thenReturn(LocalDateTime.of(2019, 4, 23, 22, 12, 47));
        when(inquiry1.getUpdated()).thenReturn(LocalDateTime.of(2019, 4, 23, 22, 12, 47));

        inquiryList = new ArrayList<>();
        inquiryList.addAll(Arrays.asList(inquiry0, inquiry1));

        inquiryRepository = mock(InquiryRepository.class);
        when(inquiryRepository.findAll()).thenReturn(inquiryList);
        when(inquiryRepository.findById(0L)).thenReturn(Optional.of(inquiry0));
        when(inquiryRepository.findById(1L)).thenReturn(null);
        when(inquiryRepository.existsById(0L)).thenReturn(true);
        when(inquiryRepository.existsById(1L)).thenReturn(false);

        inquiryService = new InquiryService(inquiryRepository);
    }

    @Test
    public void updateInquiryWhichExists() {
        Inquiry tmpInquiry = inquiryList.get(0);
        inquiryService.updateInquiry(tmpInquiry);
        verify(inquiryRepository).save(tmpInquiry);
    }

    @Test
    public void updateInquiryWhichNotExists() {
        Inquiry tmpInquiry = inquiryList.get(1);
        inquiryService.updateInquiry(tmpInquiry);
        verify(inquiryRepository, never()).save(tmpInquiry);
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
}