package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Article.IServices.IBorrowArticleService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.DTOs.BorrowInquiryRequestDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.DTOs.BorrowInquiryResponseDTO;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Exceptions.ArticleNotAvailableException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Exceptions.InvalidLendingPeriodException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.IServices.IBorrowInquiryService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchArticleException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IInquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BorrowInquiryService implements IBorrowInquiryService {

    private final IInquiryRepository inquiries;

    private final IApplicationUserService userService;
    private final IBorrowArticleService articleService;

    @Override
    public Inquiry createInquiry(Authentication auth, BorrowInquiryRequestDTO dto)
            throws ArticleNotAvailableException, InvalidLendingPeriodException, NoSuchArticleException {
        ApplicationUser currentUser = userService.getCurrentUser(auth);

        if (isInvalidPeriod(dto)) throw new InvalidLendingPeriodException();
        if (hasDateConflict(dto)) throw new ArticleNotAvailableException();

        Inquiry inquiry = buildInquiry(dto);
        inquiry.setBorrower(currentUser);
        inquiries.save(inquiry);

        return inquiry;
    }

    @Override
    public List<BorrowInquiryResponseDTO> retrieveAllInquiriesByUser(ApplicationUser user) {
        List<Inquiry> inquiryList = inquiries.findAllByBorrower_Id(user.getId());

        return inquiryList.stream()
                .map(BorrowInquiryResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowInquiryResponseDTO> retrieveAllUnacceptedInquiriesByUser(ApplicationUser user) {
        List<Inquiry> inquiryList = inquiries.findAllByBorrower_IdAndStatusNot(user.getId(), Inquiry.Status.ACCEPTED);

        return inquiryList.stream()
                .map(BorrowInquiryResponseDTO::new)
                .collect(Collectors.toList());
    }

    private Inquiry buildInquiry(BorrowInquiryRequestDTO dto) throws NoSuchArticleException {
        Inquiry inquiry = new Inquiry();

        BorrowArticle borrowArticle = articleService.getArticleById(dto.getArticleId());

        inquiry.setBorrowArticle(borrowArticle);
        inquiry.setLender(borrowArticle.getOwner());
        inquiry.setStatus(Inquiry.Status.OPEN);
        inquiry.setStartDate(dto.getStartDate());
        inquiry.setEndDate(dto.getEndDate());

        return inquiry;
    }

    private boolean hasDateConflict(BorrowInquiryRequestDTO dto) {
        List<Inquiry> allConflictingInquiries =
                inquiries.findAllByBorrowArticle_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        dto.getArticleId(), dto.getEndDate(), dto.getStartDate());
        List<Inquiry> openOrAcceptedInquiries = getOpenAndAcceptedInquiries(allConflictingInquiries);
        return !openOrAcceptedInquiries.isEmpty();
    }

    private boolean isInvalidPeriod(BorrowInquiryRequestDTO dto) {
        boolean endBeforeStart = dto.getEndDate().isBefore(dto.getStartDate());
        boolean startInPast = dto.getStartDate().isBefore(LocalDate.now());
        return endBeforeStart || startInPast;
    }

    public List<Inquiry> getOpenAndAcceptedInquiries(List<Inquiry> inquiryList) {
        List<Inquiry> openAcceptedInquiries = new ArrayList<>();

        for (Inquiry inquiry : inquiryList) {
            Inquiry.Status status = inquiry.getStatus();
            if (status == Inquiry.Status.OPEN || status == Inquiry.Status.ACCEPTED) {
                openAcceptedInquiries.add(inquiry);
            }
        }
        return openAcceptedInquiries;

    }

}