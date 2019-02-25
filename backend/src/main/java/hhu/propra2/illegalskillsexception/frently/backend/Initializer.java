package hhu.propra2.illegalskillsexception.frently.backend;

import com.github.javafaker.Faker;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Role;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IApplicationUserRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IArticleRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class Initializer implements ServletContextInitializer {
    private final IArticleRepository articleRepo;
    private final IApplicationUserRepository userRepo;
    private final IApplicationUserService userService;
    private final IRoleRepository roleRepository;

    @Override
    public void onStartup(final ServletContext servletContext) {

        final Faker faker = new Faker(Locale.ENGLISH);

        // Create Roles
        String[] names = {"Admin"};
        List<Role> roles = Arrays.stream(names).map(value -> {
            Role r = new Role();
            r.setName(value);
            r.setDescription(value);
            return r;
        }).collect(Collectors.toList());
        roles.forEach(this.roleRepository::save);
        System.out.println(roles);


        // Create Users
        if (userRepo.findAll().isEmpty()) {


            ApplicationUser[] fakeUsers = IntStream.range(0, 99).mapToObj(value -> {
                ApplicationUser u = new ApplicationUser();
                u.setUsername(faker.name().username());
                u.setPassword(faker.internet().password());
                u.setEmail(faker.internet().emailAddress(u.getUsername()));
                return u;
            }).toArray(ApplicationUser[]::new);

            // Set std user
            ApplicationUser standardUser = fakeUsers[0];
            standardUser.setUsername("user");
            standardUser.setPassword("password");

            Set<Role> userRoles = Collections.singleton(roles.get(0));
            standardUser.setRoles(userRoles);

            // Encrypt password
            Arrays.stream(fakeUsers).forEach(user -> {
                this.userService.encryptPassword(user);
                this.userRepo.save(user);
            });

            // Create Articles
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
                this.articleRepo.save(a);
                System.out.println(a);
            });
        }
    }
}
