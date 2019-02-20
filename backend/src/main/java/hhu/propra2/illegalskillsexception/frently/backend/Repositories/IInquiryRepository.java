package hhu.propra2.illegalskillsexception.frently.backend.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IInquiryRepository extends CrudRepository<Inquiry, Long> {
    List<Inquiry> findAll();

    List<Inquiry> findAllByLender_Id(Long lenderId);

    List<Inquiry> findAllByBorrower_Id(Long borrowerId);
}
