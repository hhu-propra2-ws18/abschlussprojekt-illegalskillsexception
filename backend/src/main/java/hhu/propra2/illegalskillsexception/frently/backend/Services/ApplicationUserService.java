package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.UserAlreadyExistsAuthenticationException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationUserService {
    private IApplicationUserRepository userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // CRUD

    public void createUser(String email, String username, String password, String bankAccount) {
        ApplicationUser temp = new ApplicationUser();
        temp.setEmail(email);
        temp.setUsername(username);
        temp.setPassword(password);
        temp.setBankAccount(bankAccount);

        userRepo.save(temp);
    }

    public void createUser(ApplicationUser user) {
        if (!userRepo.existsByUsername(user.getUsername())) {
            userRepo.save(user);
        } else {
            throw new UserAlreadyExistsAuthenticationException("The username is already in use");
        }
    }

    public ApplicationUser getUserById(Long userId) {
        Optional<ApplicationUser> userOpt = userRepo.findById(userId);

        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepo.findAll();
    }

    public ApplicationUser updateUser(ApplicationUser updateUser) {
        userRepo.save(updateUser);
        return updateUser;
    }

    public void deleteUser(long userId) {
        userRepo.deleteById(userId);
    }

    // Security

    public void encryptPassword(ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }


}
