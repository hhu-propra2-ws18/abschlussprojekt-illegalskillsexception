package hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BorrowArticle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBorrowArticleRepository extends CrudRepository<BorrowArticle, Long> {
    List<BorrowArticle> findAll();

    List<BorrowArticle> findAllByOwner_Id(Long ownerId);
}
