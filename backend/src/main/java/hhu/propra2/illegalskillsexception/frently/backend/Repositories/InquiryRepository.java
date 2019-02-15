package hhu.propra2.illegalskillsexception.frently.backend.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InquiryRepository extends CrudRepository<Inquiry, Long> {
    List<Inquiry> findAll();
}
