package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.MoneyTransfer;

import java.util.List;

public interface IMoneyTransferService {
    void createMoneyTransfer(String source, String target, Double amount);

    List<MoneyTransfer> getAll(String userName);
}
