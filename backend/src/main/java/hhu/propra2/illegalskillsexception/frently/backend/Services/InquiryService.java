package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.LendingPeriod;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.InquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService implements IInquiryService{

    private final InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository pInquiryRepository) {
        this.inquiryRepository = pInquiryRepository;
    }

    public long createInquiry(Article article, ApplicationUser borrower,
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

    public Inquiry getInquiry(long id) {
        if (inquiryRepository.existsById(id)) {
            return inquiryRepository.findById(id).get();
        }
        return null;
    }

    public List<Inquiry> getAllInquirys() {
        return inquiryRepository.findAll();
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
}
