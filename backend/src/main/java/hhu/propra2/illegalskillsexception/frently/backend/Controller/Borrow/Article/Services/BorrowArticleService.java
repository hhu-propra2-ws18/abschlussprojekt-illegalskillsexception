package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IBorrowArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IBorrowArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BorrowArticleService implements IBorrowArticleService {

    private final IBorrowArticleRepository articles;
    private final IApplicationUserService userService;

    @Override
    public List<BorrowArticle> retrieveAllButOwn(Authentication auth) throws Exception {
        long currentUserId = userService.getCurrentUser(auth).getId();
        return articles.findAllByOwner_IdNot(currentUserId);
    }

    @Override
    public List<BorrowArticle> retrieveAllOfOwner(long ownerId) throws Exception {
        return articles.findAllByOwner_Id(ownerId);
    }

    @Override
    public BorrowArticle getArticleById(long articleId) throws NoSuchArticleException {
        Optional<BorrowArticle> opt = articles.findById(articleId);
        return opt.orElseThrow(NoSuchArticleException::new);
    }
}
