package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IApplicationUserRepository;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices.IProPayApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProPayApplicationUserService implements IProPayApplicationUserService {

    private IApplicationUserRepository userRepo;

    @Override
    public ApplicationUser getApplicationUserByUsername(String userName) {
        Optional<ApplicationUser> optUser = userRepo.findByUsername(userName);
        if (optUser.isPresent()) return optUser.get();
        throw new UsernameNotFoundException(userName);

    }
}
