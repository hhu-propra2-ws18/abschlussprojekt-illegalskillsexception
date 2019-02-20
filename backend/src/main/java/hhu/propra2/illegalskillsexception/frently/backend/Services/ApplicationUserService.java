package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.UserAlreadyExistsAuthenticationException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationUserService implements IApplicationUserService {

    private IApplicationUserRepository userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // CRUD

    @Override
    public ApplicationUser getApplicationUserByUsername(String userName) {
        Optional<ApplicationUser> optUser = userRepo.findByUsername(userName);
        if (optUser.isPresent()) return optUser.get();
        throw new UsernameNotFoundException(userName);

    }

    @Override
    public void createUser(ApplicationUser user) {
        if (!userRepo.existsByUsername(user.getUsername())) {
            userRepo.save(user);
        } else {
            throw new UserAlreadyExistsAuthenticationException("The username is already in use");
        }
    }

    @Override
    public ApplicationUser getUserById(Long userId) {
        Optional<ApplicationUser> userOpt = userRepo.findById(userId);

        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }

    @Override
    public List<ApplicationUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public ApplicationUser updateUser(ApplicationUser updateUser) {
        userRepo.save(updateUser);
        return updateUser;
    }

    @Override
    public void deleteUser(long userId) {
        userRepo.deleteById(userId);
    }

    // Security

    @Override
    public void encryptPassword(ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }


    @Override
    public ApplicationUser getCurrentUser(Authentication authentication) {
        Optional<ApplicationUser> optUser = userRepo.findByUsername((String) (authentication.getPrincipal()));
        if (optUser.isPresent()) return optUser.get();
        throw new UsernameNotFoundException((String) authentication.getPrincipal());

    }
}
