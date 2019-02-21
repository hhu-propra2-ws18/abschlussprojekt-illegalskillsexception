package hhu.propra2.illegalskillsexception.frently.backend.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IArticleRepositoryTest {

    @Autowired
    IArticleRepository articleRepo;

    @Autowired
    IApplicationUserRepository personRepo;

    @After
    public void tearDown() {
        personRepo.deleteAll();
        articleRepo.deleteAll();
    }

    @Test
    public void OwnerHasOneArticleTest() {
        ApplicationUser owner = new ApplicationUser();
        owner.setId(1L);
        owner.setUsername("Hans");
        owner.setEmail("test@mail");
        owner.setPassword("123");
        owner.setTimestamp(LocalDateTime.of(2013, 12, 18, 14, 30));
        owner.setUpdated(LocalDateTime.of(2014, 2, 18, 14, 30));

        Article test1 = new Article();
        test1.setId(2);
        test1.setTitle("Lorem Ipsum");
        test1.setOwner(owner);
        test1.setTimestamp(LocalDateTime.of(2013, 12, 18, 14, 30));
        test1.setUpdated(LocalDateTime.of(2014, 2, 18, 14, 30));

        personRepo.save(owner);
        articleRepo.save(test1);

        List<Article> articles = articleRepo.findAllByOwner_Id(1L);

        Assert.assertEquals(1, articles.size());
        Assert.assertEquals(2, articles.get(0).getId());
        Assert.assertEquals("Lorem Ipsum", articles.get(0).getTitle());
        Assert.assertEquals(1, articles.get(0).getOwner().getId());
    }

    @Test
    public void OwnerHasNoArticles() {
        List<Article> articles = articleRepo.findAllByOwner_Id(5L);

        Assert.assertEquals(0, articles.size());
    }

    @Test
    public void OwnerHasTwoArticleTest() {
        ApplicationUser owner = new ApplicationUser();
        owner.setUsername("Johann");
        owner.setEmail("Johann@mail");
        owner.setPassword("321");
        owner.setTimestamp(LocalDateTime.of(2013, 12, 18, 14, 30));
        owner.setUpdated(LocalDateTime.of(2014, 2, 18, 14, 30));

        Article dishwasher = new Article();
        dishwasher.setTitle("Washes dishes.");
        dishwasher.setOwner(owner);
        dishwasher.setTimestamp(LocalDateTime.of(2013, 12, 18, 14, 30));
        dishwasher.setUpdated(LocalDateTime.of(2014, 2, 18, 14, 30));

        Article theDevice9000 = new Article();
        theDevice9000.setTitle("The Device9000 starts earlier than normal workers.");
        theDevice9000.setOwner(owner);
        theDevice9000.setTimestamp(LocalDateTime.of(2013, 12, 18, 14, 30));
        theDevice9000.setUpdated(LocalDateTime.of(2014, 2, 18, 14, 30));

        personRepo.save(owner);
        articleRepo.save(dishwasher);
        articleRepo.save(theDevice9000);

        List<Article> articles = articleRepo.findAllByOwner_Id(3L);

        Assert.assertEquals(2, articles.size());
    }
}