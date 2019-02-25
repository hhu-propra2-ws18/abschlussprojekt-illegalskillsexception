package hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionRepository extends CrudRepository <Transaction, Long> {
    List<Transaction> findAll();

    List<Transaction> findAllByInquiry_Borrower_Id(long id);

    List<Transaction> findAllByInquiry_Borrower_IdAndInquiry_EndDateLessThan(long userId, LocalDate nowDate);

    List<Transaction> findAllByInquiry_Lender_Id(long id);
}
