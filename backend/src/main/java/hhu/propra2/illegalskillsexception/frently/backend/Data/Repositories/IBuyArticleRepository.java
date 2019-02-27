package hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.BuyArticle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBuyArticleRepository extends CrudRepository<BuyArticle, Long> {

    List<BuyArticle> findAll();

    List<BuyArticle> findAllByOwner_Username(String username);
}
