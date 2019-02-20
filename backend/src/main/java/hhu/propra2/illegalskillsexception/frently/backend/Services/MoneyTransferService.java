package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Models.MoneyTransfer;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IMoneyTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyTransferService implements IMoneyTransferService {
    private IApplicationUserService applicationUserService;
    private IMoneyTransferRepository moneyTransferRepository;

    @Autowired
    public MoneyTransferService(IApplicationUserService applicationUserService, IMoneyTransferRepository moneyTransferRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.applicationUserService = applicationUserService;
    }

    @Override
    public void createMoneyTransfer(String source, String target, Double amount) {
        MoneyTransfer moneyTransfer = new MoneyTransfer();
        ApplicationUser user = applicationUserService.getApplicationUserByUsername(source);
        moneyTransfer.setApplicationUser(user);
        moneyTransfer.setAmount(amount);
        moneyTransfer.setTargetUserName(target);
        moneyTransferRepository.save(moneyTransfer);
    }

    public List<MoneyTransfer> getAll(String userName) {
        return moneyTransferRepository.findAllByApplicationUser_Username(userName);
    }
}
