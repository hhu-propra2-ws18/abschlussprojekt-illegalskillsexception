package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IApplicationUserRepository;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProPayApplicationUserService implements IProPayApplicationUserService {

    private IApplicationUserRepository userRepo;

    @Override
    public ApplicationUser getApplicationUserByUsername(String userName) throws UserNotFoundException {
        return userRepo.findByUsername(userName).orElseThrow(UserNotFoundException::new);

    }
}
