package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.ProPayAccount;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services.ProPayService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.ArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.InquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propay")
public class TestController {
    @Autowired
    private ProPayService proPayService;
    final String TESTNAME1 = "florian";
    final String TESTNAME2 = "tim";
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private InquiryService inquiryService;

    @GetMapping("/test1")
    public String test1() {

        ApplicationUser florian = new ApplicationUser();
        florian.setUsername(TESTNAME1);
        florian.setPassword("123");

        ApplicationUser tim = new ApplicationUser();
        tim.setUsername(TESTNAME2);
        tim.setPassword("123");

        applicationUserService.createUser(florian);
        applicationUserService.createUser(tim);

        return "test1";
    }


    @GetMapping("/test2")
    public String test2() {
        ProPayAccount florianAcc = proPayService.createAccount(TESTNAME1, 300);
        System.out.println(florianAcc);
        ProPayAccount timAcc = proPayService.createAccount(TESTNAME2, 400);
        System.out.println(timAcc);
        return "test2";
    }

    @GetMapping("/test3")
    public String test3() {
        double balance1 = proPayService.getAccountBalance(TESTNAME1);
        System.out.println(balance1);
        double balance2 = proPayService.getAccountBalance(TESTNAME2);
        System.out.println(balance2);

        return "test3";
    }

    @GetMapping("/test4")
    public String test4() {
        Transaction transaction = new Transaction();
        transaction.setId(0);
        proPayService.blockDeposit(TESTNAME1, TESTNAME2, 200, transaction);
        System.out.println(proPayService.getProPayAccount(TESTNAME1));
        System.out.println(proPayService.getProPayAccount(TESTNAME2));
        proPayService.punishUser(TESTNAME1, transaction);
        System.out.println(proPayService.getProPayAccount(TESTNAME1));
        System.out.println(proPayService.getProPayAccount(TESTNAME2));
        return "test4";
    }

    @GetMapping("/test5")
    public String test5() {
        System.out.println(proPayService.getAccountBalance(TESTNAME1));
        System.out.println(proPayService.getAccountBalance(TESTNAME2));

        proPayService.transferMoney(TESTNAME1, TESTNAME2, 1);

        System.out.println(proPayService.getAccountBalance(TESTNAME1));
        System.out.println(proPayService.getAccountBalance(TESTNAME2));


        return "test5";
    }


    private String test6(Transaction transaction) {


        return "test6";
    }
}
