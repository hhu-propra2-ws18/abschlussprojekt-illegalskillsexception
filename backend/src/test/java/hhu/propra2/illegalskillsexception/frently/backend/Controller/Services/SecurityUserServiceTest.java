package hhu.propra2.illegalskillsexception.frently.backend.Controller.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services.SecurityUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Role;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Repositories.IApplicationUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecurityUserServiceTest {

    private IApplicationUserRepository userRepository;
    private SecurityUserService userDetailsService;

    @Before
    public void setUp() {
        userRepository = mock(IApplicationUserRepository.class);
        userDetailsService = new SecurityUserService(userRepository);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadInvalidUserByUsername() {
        when(userRepository.findByUsername("Karl")).thenReturn(Optional.empty());
        userDetailsService.loadUserByUsername("Karl");
    }

    @Test
    @WithMockUser(username = "Karl")
    public void loadValidUserByUsername() {
        Role userRole = new Role();
        userRole.setId(0L);
        userRole.setName("USER");
        userRole.setDescription("Basic user");

        HashSet<Role> roles = new HashSet<>();
        roles.add(userRole);

        ApplicationUser user = new ApplicationUser();
        user.setUsername("Karl");
        user.setPassword("password");
        user.setRoles(roles);

        when(userRepository.findByUsername("Karl")).thenReturn(Optional.of(user));

        userDetailsService.loadUserByUsername("Karl");
    }
}