package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IApplicationUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    private IApplicationUserRepository userRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() {
        userRepository = mock(IApplicationUserRepository.class);
        when(userRepository.findByUsername("Test")).thenReturn(Optional.empty());
        when(userRepository.findByUsername("NoName")).thenReturn(Optional.of(new ApplicationUser()));
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadInvalidUserByUsername() {
        userDetailsService.loadUserByUsername("Test");
        verify(userRepository.findByUsername("Test"));
    }

    @Test
    public void loadValidUserByUsername() {
        //TODO: Fill this test
    }
}