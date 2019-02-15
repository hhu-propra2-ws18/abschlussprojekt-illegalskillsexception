package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {
    private IArticleRepository articleRepo;

    @Autowired
    public ArticleService(IArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }


    public void createArticle(ApplicationUser owner, Article article) {
        article.setOwner(owner);
        articleRepo.save(article);
    }

    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    public Article getArticleById(Long id) {
        Optional<Article> articleOpt = articleRepo.findById(id);

        if (articleOpt.isPresent()) {
            return articleOpt.get();
        } else {
            return null;
        }
    }

    public List<Article> getAllArticlesOfOwner(ApplicationUser owner) {
        return articleRepo.findAllByOwner_Id(owner.getId());
    }

    public Article updateArticle(Long articleId, String title, int deposit, String description, int dailyRate) {
        Optional<Article> toUpdateOpt = articleRepo.findById(articleId);

        if (toUpdateOpt.isPresent()) {
            Article toUpdate = toUpdateOpt.get();

            toUpdate.setTitle(title);
            toUpdate.setDeposit(deposit);
            toUpdate.setDescription(description);
            toUpdate.setDailyRate(dailyRate);

            return articleRepo.save(toUpdate);
        } else {
            return null;
        }
    }

    public void updateArticle(Article article) {
        articleRepo.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepo.deleteById(id);
    }
}
