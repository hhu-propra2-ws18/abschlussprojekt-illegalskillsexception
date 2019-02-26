package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class SecurityUserService implements UserDetailsService {
    private IApplicationUserRepository applicationUserRepository;

    @Autowired
    public SecurityUserService(IApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> optUser = applicationUserRepository.findByUsername(username);
        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        ApplicationUser applicationUser = optUser.get();
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}