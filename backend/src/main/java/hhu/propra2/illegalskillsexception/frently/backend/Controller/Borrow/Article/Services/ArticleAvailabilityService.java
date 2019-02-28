package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.DTOs.ArticleAvailabilityDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IArticleAvailabilityService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleAvailabilityService implements IArticleAvailabilityService {

    private final IInquiryRepository inquiryRepo;

    @Override
    public ArticleAvailabilityDTO getArticleAvailabilityDTP(long articleId) {
//        List<Inquiry> matchingInquiries = inquiryRepo.findAllByBorrowArticle_Id(articleId);
        List<Inquiry> matchingInquiries = inquiryRepo.findAllByBorrowArticle_Id(articleId);
        ArrayList<String> list = new ArrayList<>();
        matchingInquiries.stream().map(Inquiry::timeSpanToString).forEach(list::add);

        ArticleAvailabilityDTO dto = new ArticleAvailabilityDTO();
        dto.setBlockedTimespans(list);
        return dto;
    }
}
