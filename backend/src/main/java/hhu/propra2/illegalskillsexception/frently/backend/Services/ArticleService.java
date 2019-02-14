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

    List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    Article getArticleById(Long id) {
        Optional<Article> p = articleRepo.findById(id);
        if (p.isPresent()) {
            return p.get();
        } else {
            // TODO Error Handling
            return null;
        }
    }

    List<Article> getAllArticlesFromOwner(ApplicationUser owner) {
        return articleRepo.findAllByOwner_Id(owner.getId());
    }

    void updateArticle(Article toUpdate) {
        articleRepo.save(toUpdate);
    }

    void deleteArticle(Long id) {
        articleRepo.deleteById(id);
    }
}
