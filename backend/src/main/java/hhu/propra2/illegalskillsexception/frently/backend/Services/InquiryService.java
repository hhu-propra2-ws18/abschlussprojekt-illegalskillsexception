package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.*;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry.Status.accepted;

@Service
public class InquiryService implements IInquiryService {

    private final InquiryRepository inquiryRepository;
    private ITransactionService transactionservice;

    @Autowired
    public InquiryService(InquiryRepository pInquiryRepository, ITransactionService transactionService) {
        this.inquiryRepository = pInquiryRepository;
        this.transactionservice = transactionService;
    }

    public Long createInquiry(Article article, ApplicationUser borrower,
                              LendingPeriod lendingPeriod, Inquiry.Status status) {
        final Inquiry temp = setInquiry(new Inquiry(), article, borrower, article.getOwner(), lendingPeriod, status);
        inquiryRepository.save(temp);
        return temp.getId();
    }

    public Inquiry updateInquiry(Inquiry inquiry) {
        if (inquiryRepository.existsById(inquiry.getId())) {
            inquiryRepository.save(inquiry);
        }
        return inquiry;
    }

    public Inquiry getInquiry(Long id) {
        if (inquiryRepository.existsById(id)) {
            return inquiryRepository.findById(id).get();
        }
        return null;
    }

    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    public List<Inquiry> getAllInquiries(Long id) {
        List<Inquiry> list = inquiryRepository.findAllByBorrower_Id(id);
        list.addAll(inquiryRepository.findAllByLender_Id(id));

        return list;
    }

    private Inquiry setInquiry(Inquiry inquiry, Article article, ApplicationUser borrower,
                               ApplicationUser lender, LendingPeriod lendingPeriod, Inquiry.Status status) {
        inquiry.setArticle(article);
        inquiry.setBorrower(borrower);
        inquiry.setLender(lender);
        inquiry.setDuration(lendingPeriod);
        inquiry.setStatus(status);
        return inquiry;
    }

    public void accept(ApplicationUser borrower, Long inquiryId) throws Exception {
        Inquiry inquiry = getInquiry(inquiryId);

        Double prize = calculateAccumulatedDailyRate(inquiry);
        Double deposit = inquiry.getArticle().getDeposit();
        ProPayAccount proPayAccountBorrower = getProPayAccount(borrower.getBankAccount());
        String accountLender = inquiry.getLender().getBankAccount();

        if (hasEnoughMoney(proPayAccountBorrower, prize + deposit)) {
            transactionservice.createTransaction(Transaction.Status.open, inquiry);
            blockMoney(proPayAccountBorrower, accountLender, deposit);
            transferMoney(proPayAccountBorrower, accountLender, prize);
            inquiry.setStatus(accepted);
        } else throw new Exception();
    }

    private void transferMoney(ProPayAccount borrower, String lender, Double prize) {
        final String url = "http://localhost:8080/Propay/account/" + borrower.getAccount() + "/transfer/" + lender + "?amount=" + prize;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(url, null);
    }

    private void blockMoney(ProPayAccount accountBorrower, String accountLender, Double prize) {
        final String url = "http://localhost:8080/Propay/reservation/reserve/" + accountBorrower.getAccount() + "/" + accountLender + "?amount=" + prize;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(url, null);
    }

    public void decline(ApplicationUser borrower, Long inquiryId) {
        Inquiry inquiry = getInquiry(inquiryId);
        inquiry.setStatus(Inquiry.Status.declined);
    }

    Boolean hasEnoughMoney(ProPayAccount account, Double requestedMoney) {
        return account.getAmount() >= requestedMoney;
    }

    private ProPayAccount getProPayAccount(String account) {
        final String url = "http://localhost:8080/ProPay/account/" + account;
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, ProPayAccount.class);
    }

    Double calculateAccumulatedDailyRate(Inquiry inquiry) {
        Article article  = inquiry.getArticle();
        Double dailyRate = article.getDailyRate();
        LendingPeriod lendingPeriod = inquiry.getDuration();
        Long length = lendingPeriod.getLengthInDays();

        return dailyRate * length;
    }
}
