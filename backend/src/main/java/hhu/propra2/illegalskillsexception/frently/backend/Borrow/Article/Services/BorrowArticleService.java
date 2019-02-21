package hhu.propra2.illegalskillsexception.frently.backend.Borrow.Article.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BorrowArticleService implements IBorrowArticleService {

    private final IArticleRepository articles;

    @Override
    public List<Article> retrieveAll() {
        return articles.findAll();
    }

    @Override
    public List<Article> retrieveAllOfOwner(long ownerId) {
        return articles.findAllByOwner_Id(ownerId);
    }
}
