package hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface IInquiryRepository extends CrudRepository<Inquiry, Long> {
    List<Inquiry> findAll();

    List<Inquiry> findAllByLender_Id(Long lenderId);

    List<Inquiry> findAllByBorrower_Id(Long borrowerId);

    List<Inquiry> findAllByBorrower_IdAndStatusNot(Long borrowerId, Inquiry.Status status);

    List<Inquiry> findAllByArticle_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(long articleId, LocalDate startDate, LocalDate endDate);

    List<Inquiry> findAllByLender_IdAndStatus(Long lenderId, Inquiry.Status status);
    List<Inquiry> findAllByBorrowArticle_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(long articleId, LocalDate startDate, LocalDate endDate);
}
