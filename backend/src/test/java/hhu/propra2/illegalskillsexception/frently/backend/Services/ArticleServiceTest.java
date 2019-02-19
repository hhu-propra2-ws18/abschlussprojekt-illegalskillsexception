package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IArticleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class ArticleServiceTest {

    @Test
    public void articleOfId1NotFoundThenReturnsNull() {
        IArticleRepository repoMock = Mockito.mock(IArticleRepository.class);
        ArticleService service = new ArticleService(repoMock);

        Optional<Article> opt = Optional.empty();
        when(repoMock.findById(1L)).thenReturn(opt);

        Article article = service.getArticleById(1L);

        Assert.assertNull(article);
    }

    @Test
    public void articleOfId1FoundThenArticleReturned() {
        IArticleRepository repoMock = Mockito.mock(IArticleRepository.class);
        ArticleService service = new ArticleService(repoMock);

        Article articleInDatabase = new Article();
        articleInDatabase.setId(1L);
        Optional<Article> opt = Optional.of(articleInDatabase);

        when(repoMock.findById(1L)).thenReturn(opt);

        Article article = service.getArticleById(1L);

        Assert.assertEquals(articleInDatabase, article);
    }

    @Test
    public void articleThatShouldBeUpdatedIsPresentAndUpdated() {
        IArticleRepository repoMock = Mockito.mock(IArticleRepository.class);
        ArticleService service = new ArticleService(repoMock);

        Article articleInDatabase = new Article();
        articleInDatabase.setId(1L);
        articleInDatabase.setDescription("old");

        Optional<Article> opt = Optional.of(articleInDatabase);
        when(repoMock.findById(1L)).thenReturn(opt);
        when(repoMock.save(articleInDatabase)).thenReturn(articleInDatabase);

        Article article = service.updateArticle(1L, "title", 300., "new", 100.);

        Assert.assertEquals("new", article.getDescription());
    }

    @Test
    public void articleThatShouldBeUpdatedIsNotPresentThenNullIsReturned() {
        IArticleRepository repoMock = Mockito.mock(IArticleRepository.class);
        ArticleService service = new ArticleService(repoMock);

        Optional<Article> opt = Optional.empty();
        when(repoMock.findById(1L)).thenReturn(opt);

        Article article = service.updateArticle(1L, "title", 300., "new", 100.);

        Assert.assertNull(article);
    }

}