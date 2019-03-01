package hhu.propra2.illegalskillsexception.frently.backend;

import com.github.javafaker.Faker;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.*;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.*;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayService;
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
    private final IBorrowArticleRepository articleRepo;
    private final IInquiryRepository inquiryRepo;
    private final ITransactionRepository transactionRepo;
    private final IApplicationUserRepository userRepo;
    private final IApplicationUserService userService;
    private final IRoleRepository roleRepository;
    private final IMoneyTransferRepository moneyTransferRepo;
    private final IProPayService proPayService;
    private final IBuyArticleRepository buyArticleRepo;

    @Override
    public void onStartup(final ServletContext servletContext) {

        final Faker faker = new Faker(Locale.ENGLISH);

        // Create Roles
        String[] names = {"ADMIN", "USER"};
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
            standardUser.setUsername("admin");
            standardUser.setPassword("password");

            Set<Role> userRoles = Collections.singleton(roles.get(0));
            standardUser.setRoles(userRoles);

            ApplicationUser standardUserBorrow = fakeUsers[1];
            standardUserBorrow.setUsername("user");
            standardUserBorrow.setPassword("password");


            // Encrypt password
            Arrays.stream(fakeUsers).forEach(user -> {
                this.userService.encryptPassword(user);
                this.userRepo.save(user);
            });
            try {
                proPayService.createAccount("user", 100000);
                proPayService.createAccount("user1", 0);
            } catch (Exception exc) {
                System.out.println("Propay not found");
                exc.printStackTrace();
            }

            //Create buyable articles
            BuyArticle first = new BuyArticle();
            first.setOwner(standardUserBorrow);
            first.setLocation("Le louvre");
            first.setPrice(100.0);
            first.setTitle("Some Davinci painting");
            first.setDescription("Some stuff");
            buyArticleRepo.save(first);

            BuyArticle second = new BuyArticle();
            second.setOwner(standardUser);
            second.setLocation("Le louvre");
            second.setPrice(10000.0);
            second.setTitle("Some dude looking at fog");
            second.setDescription("I thinks it's a painting by some guy called Casper David Friedrich");
            buyArticleRepo.save(second);


            // Create borrowable articles (please dont kill me for that word)
            BorrowArticle standardZeroBorrowArticle = new BorrowArticle();
            standardZeroBorrowArticle.setTitle("A painting");
            standardZeroBorrowArticle.setDescription("Some famous painting ending in isa");
            standardZeroBorrowArticle.setDailyRate(0.0);
            standardZeroBorrowArticle.setDeposit(0.0);
            standardZeroBorrowArticle.setLocation("Le Louvre");
            standardZeroBorrowArticle.setOwner(standardUser);
            this.articleRepo.save(standardZeroBorrowArticle);

            BorrowArticle standardNotZeroBorrowArticle = new BorrowArticle();
            standardNotZeroBorrowArticle.setTitle("Another painting");
            standardNotZeroBorrowArticle.setDescription("Some famous painting ending in isa");
            standardNotZeroBorrowArticle.setDailyRate(20.0);
            standardNotZeroBorrowArticle.setDeposit(100.0);
            standardNotZeroBorrowArticle.setLocation("Le Louvre");
            standardNotZeroBorrowArticle.setOwner(standardUser);
            this.articleRepo.save(standardNotZeroBorrowArticle);

            Inquiry inquiryZeroArticle = new Inquiry();
            inquiryZeroArticle.setBorrowArticle(standardZeroBorrowArticle);
            inquiryZeroArticle.setStatus(Inquiry.Status.ACCEPTED);
            inquiryZeroArticle.setLender(standardUser);
            inquiryZeroArticle.setBorrower(standardUserBorrow);
            inquiryZeroArticle.setStartDate(LocalDate.of(2019, 1, 12));
            inquiryZeroArticle.setEndDate(LocalDate.of(2019, 1, 22));
            this.inquiryRepo.save(inquiryZeroArticle);

            Inquiry inquiryNotZeroArticle = new Inquiry();
            inquiryNotZeroArticle.setBorrowArticle(standardNotZeroBorrowArticle);
            inquiryNotZeroArticle.setStatus(Inquiry.Status.OPEN);
            inquiryNotZeroArticle.setLender(standardUser);
            inquiryNotZeroArticle.setBorrower(standardUserBorrow);
            inquiryNotZeroArticle.setStartDate(LocalDate.of(2019, 1, 12));
            inquiryNotZeroArticle.setEndDate(LocalDate.of(2019, 2, 22));
            this.inquiryRepo.save(inquiryNotZeroArticle);

            Transaction transactionFromInquiryZeroArticle = new Transaction();
            transactionFromInquiryZeroArticle.setStatus(Transaction.Status.OPEN);
            transactionFromInquiryZeroArticle.setInquiry(inquiryNotZeroArticle);
            transactionFromInquiryZeroArticle.setReservationId(-1);
            transactionFromInquiryZeroArticle.setReturnDate(inquiryNotZeroArticle.getEndDate());
            this.transactionRepo.save(transactionFromInquiryZeroArticle);

            Transaction transactionConflict = new Transaction();
            transactionConflict.setStatus(Transaction.Status.CONFLICT);
            transactionConflict.setInquiry(inquiryNotZeroArticle);
            transactionConflict.setReservationId(-1);
            transactionConflict.setReturnDate(inquiryNotZeroArticle.getEndDate());
            this.transactionRepo.save(transactionConflict);

            IntStream.range(0, 99).mapToObj(value -> {
                BorrowArticle a = new BorrowArticle();
                a.setTitle(faker.commerce().productName());
                a.setDeposit(faker.number().randomDouble(2, 10, 300));
                a.setDailyRate(faker.number().randomDouble(2, 1, 100));
                int randomIndex = faker.number().numberBetween(0, 99);
                a.setOwner(fakeUsers[randomIndex]);
                a.setDescription(faker.lorem().paragraph(3));
                a.setLocation(faker.address().fullAddress());

                return a;
            }).forEach(a -> {
                this.articleRepo.save(a);
                System.out.println(a);
            });
            IntStream.range(0, 99).mapToObj(value -> {
                BuyArticle a = new BuyArticle();
                a.setTitle(faker.commerce().productName());
                a.setPrice(faker.number().randomDouble(2, 10, 300));
                int randomIndex = faker.number().numberBetween(0, 99);
                a.setOwner(fakeUsers[randomIndex]);
                a.setDescription(faker.lorem().paragraph(5));
                a.setLocation(faker.address().fullAddress());

                return a;
            }).forEach(a -> {
                this.buyArticleRepo.save(a);
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
