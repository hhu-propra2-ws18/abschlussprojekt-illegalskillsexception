package hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMoneyTransferRepository extends CrudRepository<MoneyTransfer, Long> {
    List<MoneyTransfer> findAll();

    List<MoneyTransfer> findAllBySender_UsernameOrReceiver_Username(String userNameSender, String userNameReceiver);

    //List<MoneyTransfer> findAllByApplicationUser_Username(String userName);
}
