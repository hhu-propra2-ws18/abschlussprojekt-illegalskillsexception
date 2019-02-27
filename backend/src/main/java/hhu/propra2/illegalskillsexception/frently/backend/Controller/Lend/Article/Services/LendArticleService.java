package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.DTOs.LendArticleUpdate;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.Exceptions.PendingInquiryException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.IServices.ILendArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IArticleRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LendArticleService implements ILendArticleService {

    private IArticleRepository articleRepo;
    private IInquiryRepository inquiryRepository;

    @Override
    public Article createArticle(Article article, ApplicationUser user) {
        article.setOwner(user);
        articleRepo.save(article);
        return article;
    }

    @Override
    public List<Article> retrieveArticleList(ApplicationUser owner) {
        return articleRepo.findAllByOwner_Id(owner.getId());
    }

    @Override
    public Article updateArticle(LendArticleUpdate lendArticle) throws NoSuchArticleException, PendingInquiryException {

        Optional<Article> articleOpt = articleRepo.findById(lendArticle.getArticleId());
        Article article = articleOpt.orElseThrow(NoSuchArticleException::new);
        if (!noPendingInquiries(article)) throw new PendingInquiryException();

        article.setTitle(lendArticle.getTitle());
        article.setDeposit(lendArticle.getDeposit());
        article.setDescription(lendArticle.getDescription());
        article.setDailyRate(lendArticle.getDailyRate());
        article.setLocation(lendArticle.getLocation());
        return articleRepo.save(article);

    }

    boolean noPendingInquiries(Article article) {
        ApplicationUser lender = article.getOwner();
        List<Inquiry> lenderInquiries = inquiryRepository.findAllByLender_IdAndStatus(lender.getId(), Inquiry.Status.OPEN);
        List<Inquiry> inquiriesForArticle = lenderInquiries.stream()
                .filter(inquiry -> (inquiry.getArticle().getId() == article.getId()))
                .collect(Collectors.toList());
        return inquiriesForArticle.isEmpty();
    }

}
