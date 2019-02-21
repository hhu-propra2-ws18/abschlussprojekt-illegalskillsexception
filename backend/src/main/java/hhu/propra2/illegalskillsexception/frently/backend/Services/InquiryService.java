package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.InquiryNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.*;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IInquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry.Status.ACCEPTED;

@Service
public class InquiryService implements IInquiryService {

    private final IInquiryRepository inquiryRepository;
    private ITransactionService transactionservice;

    @Autowired
    public InquiryService(IInquiryRepository pInquiryRepository, ITransactionService transactionService) {
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

    public Inquiry getInquiry(Long id) throws InquiryNotFoundException {
        Optional<Inquiry> opt = inquiryRepository.findById(id);
        return opt.orElseThrow(InquiryNotFoundException::new);
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
        inquiry.setStartDate(lendingPeriod.getStartLend());
        inquiry.setEndDate(lendingPeriod.getEndLend());
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
            inquiry.setStatus(ACCEPTED);
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

    public FrentlyResponse decline(ApplicationUser borrower, Long inquiryId) {
        FrentlyResponse response = new FrentlyResponse();
        try {
            Inquiry inquiry = getInquiry(inquiryId);
            inquiry.setStatus(Inquiry.Status.DECLINED);
            response.setData(inquiry);
        } catch (InquiryNotFoundException e) {
            response.setError(new FrentlyError(e.getMessage(), FrentlyErrorType.INQUIRY_NOT_FOUND));
        }
        return response;
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
        Article article = inquiry.getArticle();
        Double dailyRate = article.getDailyRate();
        LendingPeriod lendingPeriod = new LendingPeriod(inquiry.getStartDate(), inquiry.getEndDate());
        Long length = lendingPeriod.getLengthInDays();

        return dailyRate * length;
    }
}
