package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserAlreadyExistsAuthenticationException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IApplicationUserRepository;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IRoleRepository;
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
    private IRoleRepository roleRepository;

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
            Role role = roleRepository.findById(2L).orElse(null);
            user.setRoles(Collections.singleton(role));
            userRepo.save(user);
        } else {
            throw new UserAlreadyExistsAuthenticationException("The username is already in use");
        }
    }

    @Override
    public ApplicationUser getUserById(Long userId) {
        Optional<ApplicationUser> userOpt = userRepo.findById(userId);
        return userOpt.orElse(null);
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
