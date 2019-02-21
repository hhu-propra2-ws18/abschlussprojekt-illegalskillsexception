package hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;

import java.util.List;

public interface IMoneyTransferService {
    void createMoneyTransfer(String source, String target, Double amount);

    List<MoneyTransfer> getAll(String userName);
}
