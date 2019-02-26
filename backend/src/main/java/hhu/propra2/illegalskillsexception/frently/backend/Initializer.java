package hhu.propra2.illegalskillsexception.frently.backend;

import com.github.javafaker.Faker;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.*;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.*;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class Initializer implements ServletContextInitializer {
    private final IArticleRepository articleRepo;
    private final IInquiryRepository inquiryRepo;
    private final ITransactionRepository transactionRepo;
    private final IApplicationUserRepository userRepo;
    private final IApplicationUserService userService;
    private final IRoleRepository roleRepository;
    private final IMoneyTransferRepository moneyTransferRepo;


    @Override
    public void onStartup(final ServletContext servletContext) {

        final Faker faker = new Faker(Locale.ENGLISH);

        // Create Roles
        String[] names = {"ADMIN"};
        List<Role> roles = Arrays.stream(names).map(value -> {
            Role r = new Role();
            r.setName(value);
            r.setDescription(value);
            return r;
        }).collect(Collectors.toList());
        roles.forEach(this.roleRepository::save);


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

            ApplicationUser standardUserBorrow = fakeUsers[1];
            standardUserBorrow.setUsername("user1");
            standardUserBorrow.setPassword("password");

            // Encrypt password
            Arrays.stream(fakeUsers).forEach(user -> {
                this.userService.encryptPassword(user);
                this.userRepo.save(user);
            });

            // Create Articles
            Article standardZeroArticle = new Article();
            standardZeroArticle.setTitle("A painting");
            standardZeroArticle.setDescription("Some famous painting ending in isa");
            standardZeroArticle.setDailyRate(0.0);
            standardZeroArticle.setDeposit(0.0);
            standardZeroArticle.setLocation("Le Louvre");
            standardZeroArticle.setOwner(standardUser);
            this.articleRepo.save(standardZeroArticle);

            Article standardNotZeroArticle = new Article();
            standardNotZeroArticle.setTitle("Another painting");
            standardNotZeroArticle.setDescription("Some famous painting ending in isa");
            standardNotZeroArticle.setDailyRate(20.0);
            standardNotZeroArticle.setDeposit(100.0);
            standardNotZeroArticle.setLocation("Le Louvre");
            standardNotZeroArticle.setOwner(standardUser);
            this.articleRepo.save(standardNotZeroArticle);

            Inquiry inquiryZeroArticle = new Inquiry();
            inquiryZeroArticle.setArticle(standardZeroArticle);
            inquiryZeroArticle.setStatus(Inquiry.Status.ACCEPTED);
            inquiryZeroArticle.setLender(standardUser);
            inquiryZeroArticle.setBorrower(standardUserBorrow);
            inquiryZeroArticle.setStartDate(LocalDate.of(2019, 1, 12));
            inquiryZeroArticle.setEndDate(LocalDate.of(2019, 1, 22));
            this.inquiryRepo.save(inquiryZeroArticle);

            Inquiry inquiryNotZeroArticle = new Inquiry();
            inquiryNotZeroArticle.setArticle(standardNotZeroArticle);
            inquiryNotZeroArticle.setStatus(Inquiry.Status.OPEN);
            inquiryNotZeroArticle.setLender(standardUser);
            inquiryNotZeroArticle.setBorrower(standardUserBorrow);
            inquiryNotZeroArticle.setStartDate(LocalDate.of(2019, 2, 12));
            inquiryNotZeroArticle.setEndDate(LocalDate.of(2019, 1, 22));
            this.inquiryRepo.save(inquiryNotZeroArticle);

            Transaction transactionFromInquiryZeroArticle = new Transaction();
            transactionFromInquiryZeroArticle.setStatus(Transaction.Status.CLOSED);
            transactionFromInquiryZeroArticle.setInquiry(inquiryNotZeroArticle);
            transactionFromInquiryZeroArticle.setReservationId(-1);
            transactionFromInquiryZeroArticle.setReturnDate(inquiryNotZeroArticle.getEndDate());
            this.transactionRepo.save(transactionFromInquiryZeroArticle);

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

            MoneyTransfer firstTransfer = new MoneyTransfer();
            firstTransfer.setSender(standardUser);
            firstTransfer.setReceiver(standardUserBorrow);
            firstTransfer.setAmount(100);

            MoneyTransfer secondTransfer = new MoneyTransfer();
            secondTransfer.setSender(standardUser);
            secondTransfer.setReceiver(standardUserBorrow);
            secondTransfer.setAmount(3);

            MoneyTransfer thirdTransfer = new MoneyTransfer();
            thirdTransfer.setSender(standardUser);
            thirdTransfer.setReceiver(standardUserBorrow);
            thirdTransfer.setAmount(60);

            MoneyTransfer fourthTransfer = new MoneyTransfer();
            fourthTransfer.setSender(standardUser);
            fourthTransfer.setReceiver(fakeUsers[20]);
            fourthTransfer.setAmount(-1);

            MoneyTransfer fifthTransfer = new MoneyTransfer();
            fifthTransfer.setSender(fakeUsers[24]);
            fifthTransfer.setReceiver(standardUserBorrow);
            fifthTransfer.setAmount(999999);

            MoneyTransfer sixthTransfer = new MoneyTransfer();
            sixthTransfer.setSender(standardUser);
            sixthTransfer.setReceiver(standardUserBorrow);
            sixthTransfer.setAmount(0);

            moneyTransferRepo.save(firstTransfer);
            moneyTransferRepo.save(secondTransfer);
            moneyTransferRepo.save(thirdTransfer);
            moneyTransferRepo.save(fourthTransfer);
            moneyTransferRepo.save(fifthTransfer);
            moneyTransferRepo.save(sixthTransfer);

        }
    }
}
