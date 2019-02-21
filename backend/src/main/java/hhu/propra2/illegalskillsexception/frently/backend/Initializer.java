/*package hhu.propra2.illegalskillsexception.frently.backend;

import com.github.javafaker.Faker;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IApplicationUserRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IArticleRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class Initializer implements ServletContextInitializer {
    private final IArticleRepository articleRepo;
    private final IApplicationUserRepository userRepo;
    private final ApplicationUserService userService;

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        final Faker faker = new Faker(Locale.ENGLISH);
        ApplicationUser[] fakeUsers = IntStream.range(0, 99).mapToObj(value -> {
            ApplicationUser u = new ApplicationUser();
            u.setUsername(faker.name().username());
            u.setPassword(faker.internet().password());
            u.setEmail(faker.internet().emailAddress(u.getUsername()));
            return u;
        }).toArray(ApplicationUser[]::new);

        ApplicationUser standardUser = fakeUsers[0];
        standardUser.setUsername("user");
        standardUser.setPassword("password");

        Arrays.stream(fakeUsers).forEach(user -> {
            this.userService.encryptPassword(user);
            this.userRepo.save(user);
        });

        IntStream.range(0, 99).mapToObj(value -> {
            Article a = new Article();
            a.setTitle(faker.commerce().productName());
            a.setDeposit(faker.number().randomDouble(2, 10, 300));
            a.setDailyRate(faker.number().randomDouble(2, 1, 100));
            int randomIndex = faker.number().numberBetween(0, 99);
            a.setOwner(fakeUsers[randomIndex]);
            a.setDescription(faker.lorem().paragraph(3));
            a.setLocation(faker.rickAndMorty().location());

            return a;
        }).forEach(a -> {
            System.out.println(a);
            this.articleRepo.save(a);
        });
    }
}
*/