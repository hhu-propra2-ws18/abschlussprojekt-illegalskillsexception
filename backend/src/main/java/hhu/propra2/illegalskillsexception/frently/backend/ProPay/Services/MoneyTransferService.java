package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services;

import com.github.javafaker.App;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IMoneyTransferService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Models.MoneyTransfer;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IMoneyTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyTransferService implements IMoneyTransferService {
    private IProPayApplicationUserService applicationUserService;
    private IMoneyTransferRepository moneyTransferRepository;

    @Autowired
    public MoneyTransferService(IProPayApplicationUserService applicationUserService, IMoneyTransferRepository moneyTransferRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.applicationUserService = applicationUserService;
    }

    @Override
    public void createMoneyTransfer(String source, String target, Double amount) {
        MoneyTransfer moneyTransfer = new MoneyTransfer();
        ApplicationUser senderUser = applicationUserService.getApplicationUserByUsername(source);
        moneyTransfer.setSender(senderUser);
        moneyTransfer.setAmount(amount);
        ApplicationUser receiver = applicationUserService.getApplicationUserByUsername(target);
        moneyTransfer.setReceiver(receiver);
        moneyTransferRepository.save(moneyTransfer);
    }

    public List<MoneyTransfer> getAll(String userName) {
        return moneyTransferRepository.findAllBySender_UsernameOrReceiver_Username(userName,userName);
    }
}
