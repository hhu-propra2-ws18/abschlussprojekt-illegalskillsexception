package hhu.propra2.illegalskillsexception.frently.backend.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.MoneyTransfer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMoneyTransferRepository extends CrudRepository<MoneyTransfer, Long> {
    List<MoneyTransfer> findAll();

    List<MoneyTransfer> findAllByApplicationUser_Username(String userName);
}
