package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IBorrowArticleRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LendArticleServiceTest {

    private IInquiryRepository inquiryRepository;
    private IBorrowArticleRepository articleRepository;
    private LendArticleService lendArticleService;
    private BorrowArticle article;
    private List<Inquiry> inquiryList;

    @Before
    public void setup() {
        article = new BorrowArticle();
        article.setId(1L);
        ApplicationUser owner = new ApplicationUser();
        owner.setId(1L);
        article.setOwner(owner);
        Inquiry inquiry = new Inquiry();
        inquiry.setStatus(Inquiry.Status.OPEN);
        inquiry.setBorrowArticle(article);
        inquiryList = Collections.singletonList(inquiry);

        inquiryRepository = mock(IInquiryRepository.class);
        when(inquiryRepository.findAllByLender_IdAndStatus(anyLong(), any(Inquiry.Status.class))).thenReturn(new ArrayList<>());
        articleRepository = mock(IBorrowArticleRepository.class);
        lendArticleService = new LendArticleService(articleRepository, inquiryRepository);
    }

    @Test
    public void noPendingInquiriesNoInquiries() {
        boolean pendingArticles = lendArticleService.noPendingInquiries(article);
        assertTrue(pendingArticles);
    }

    @Test
    public void onePendingInquiry() {
        when(inquiryRepository.findAllByLender_IdAndStatus(anyLong(), any(Inquiry.Status.class))).thenReturn(inquiryList);
        lendArticleService = new LendArticleService(articleRepository, inquiryRepository);
        assertFalse(lendArticleService.noPendingInquiries(article));
    }
}