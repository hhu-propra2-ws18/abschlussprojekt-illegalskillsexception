package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Article;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.LendingPeriod;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.InquiryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository pInquiryRepository) {
        this.inquiryRepository = pInquiryRepository;
    }

    public void createInquiry(Article article, ApplicationUser borrower, ApplicationUser lender,
                              LendingPeriod lendingPeriod, Inquiry.State state){
        final Inquiry temp = setInquiry(new Inquiry(), article, borrower, lender, lendingPeriod, state);
        inquiryRepository.save(temp);
    }

    public void updateInquiry(Inquiry inquiry){
        if(inquiryRepository.existsById(inquiry.getId())){
            inquiryRepository.save(inquiry);
        }
    }

    public List<Inquiry> getInquiry(long id){
        if(inquiryRepository.existsById(id)){
            return Collections.singletonList(inquiryRepository.findById(id).get());
        }
        return new ArrayList<>();
    }

    public List<Inquiry> getAllInquirys(){
        return inquiryRepository.findAll();
    }

    private Inquiry setInquiry(Inquiry inquiry, Article article, ApplicationUser borrower,
                               ApplicationUser lender , LendingPeriod lendingPeriod, Inquiry.State state){
        inquiry.setArticle(article);
        inquiry.setBorrower(borrower);
        inquiry.setLender(lender);
        inquiry.setDuration(lendingPeriod);
        inquiry.setState(state);
        return inquiry;
    }
}
