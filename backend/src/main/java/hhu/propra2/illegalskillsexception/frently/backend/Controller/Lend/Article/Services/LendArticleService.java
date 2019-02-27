package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.DTOs.LendArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.IServices.ILendArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IBorrowArticleRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LendArticleService implements ILendArticleService {

    private IBorrowArticleRepository articleRepo;
    private IInquiryRepository inquiryRepository;

    @Override
    public BorrowArticle createArticle(BorrowArticle borrowArticle, ApplicationUser user) {
        borrowArticle.setOwner(user);
        articleRepo.save(borrowArticle);
        return borrowArticle;
    }

    @Override
    public List<BorrowArticle> retrieveArticleList(ApplicationUser owner) {
        return articleRepo.findAllByOwner_Id(owner.getId());
    }

    @Override
    public BorrowArticle updateArticle(LendArticleUpdate lendArticle) throws NoSuchArticleException, PendingInquiryException {

        Optional<BorrowArticle> articleOpt = articleRepo.findById(lendArticle.getArticleId());
        BorrowArticle article = articleOpt.orElseThrow(NoSuchArticleException::new);
        if (!noPendingInquiries(article)) throw new PendingInquiryException();

        article.setTitle(lendArticle.getTitle());
        article.setDeposit(lendArticle.getDeposit());
        article.setDescription(lendArticle.getDescription());
        article.setDailyRate(lendArticle.getDailyRate());
        article.setLocation(lendArticle.getLocation());
        return articleRepo.save(article);

    }

    boolean noPendingInquiries(BorrowArticle article) {
        ApplicationUser lender = article.getOwner();
        List<Inquiry> lenderInquiries = inquiryRepository.findAllByLender_IdAndStatus(lender.getId(), Inquiry.Status.OPEN);
        List<Inquiry> inquiriesForArticle = lenderInquiries.stream()
                .filter(inquiry -> (inquiry.getBorrowArticle().getId() == article.getId()))
                .collect(Collectors.toList());
        return inquiriesForArticle.isEmpty();
    }

}
