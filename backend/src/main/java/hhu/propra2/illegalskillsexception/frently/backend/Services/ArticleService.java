package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private IArticleRepository articleRepo;

    @Autowired
    public ArticleService(IArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }


    public void createArticle(String title, ApplicationUser owner, int deposit, String description, int dailyRate) {
        Article toCreate = new Article();
        toCreate.setTitle(title);
        toCreate.setOwner(owner);
        toCreate.setDeposit(deposit);
        toCreate.setDescription(description);
        toCreate.setDailyRate(dailyRate);

        articleRepo.save(toCreate);
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

    public void updateArticle(Long articleId, String title, int deposit, String description, int dailyRate) {
        Optional<Article> toUpdateOpt = articleRepo.findById(articleId);

        if (toUpdateOpt.isPresent()) {
            Article toUpdate = toUpdateOpt.get();

            toUpdate.setTitle(title);
            toUpdate.setDeposit(deposit);
            toUpdate.setDescription(description);
            toUpdate.setDailyRate(dailyRate);

            articleRepo.save(toUpdate);
        } else {
            //TODO Error handling
        }
    }

    public void deleteArticle(Long id) {
        articleRepo.deleteById(id);
    }
}
