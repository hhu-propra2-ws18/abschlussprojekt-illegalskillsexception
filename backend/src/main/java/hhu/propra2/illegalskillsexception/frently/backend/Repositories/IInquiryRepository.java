package hhu.propra2.illegalskillsexception.frently.backend.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface IInquiryRepository extends CrudRepository<Inquiry, Long> {
    List<Inquiry> findAll();
    List<Inquiry> findAllByLender_Id(Long lenderId);
    List<Inquiry> findAllByBorrower_Id(Long borrowerId);

    List<Inquiry> findAllByArticle_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(long articleId, LocalDate startDate, LocalDate endDate);
}
