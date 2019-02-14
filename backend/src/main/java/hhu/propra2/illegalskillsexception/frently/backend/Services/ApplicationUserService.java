
package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.UserAlreadyExistsAuthenticationException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ApplicationUserService {
    private IApplicationUserRepository userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(ApplicationUser user) {
        if (!userRepo.existsByUsername(user.getUsername())) {
            userRepo.save(user);
        } else {
            throw new UserAlreadyExistsAuthenticationException("The username is already in use");
        }
    }

    public void encryptPassword(ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
}
