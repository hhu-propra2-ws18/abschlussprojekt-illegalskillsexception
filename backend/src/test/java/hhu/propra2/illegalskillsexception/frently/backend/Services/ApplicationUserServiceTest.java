package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.UserAlreadyExistsAuthenticationException;
import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IApplicationUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class ApplicationUserServiceTest {

    private IApplicationUserRepository applicationUserRepository;
    private ApplicationUserService applicationUserService;
    private ArrayList<Optional> userList;
    private List<ApplicationUser> userList2;

    @Before
    public void setUp() {
        Optional<ApplicationUser> user0 = Optional.empty();
        Optional<ApplicationUser> user1 = Optional.of(new ApplicationUser());
        Optional<ApplicationUser> user2 = Optional.of(new ApplicationUser());
        userList = new ArrayList<>();
        userList.addAll(Arrays.asList(user0, user1, user2));
        userList2 = new ArrayList<>();

        ApplicationUser user3 = new ApplicationUser();
        user3.setUsername("ExampleUser");
        user3.setPassword("ExamplePassword");

        userList2.addAll(Arrays.asList(new ApplicationUser(), new ApplicationUser(), new ApplicationUser(), user3));
        applicationUserRepository = mock(IApplicationUserRepository.class);
        when(applicationUserRepository.findById(0L)).thenReturn(userList.get(0));
        when(applicationUserRepository.findById(1L)).thenReturn(userList.get(1));
        when(applicationUserRepository.findAll()).thenReturn(userList2);
        when(applicationUserRepository.existsByUsername("ExampleUser")).thenReturn(true);
        applicationUserService = new ApplicationUserService(applicationUserRepository, new BCryptPasswordEncoder());
    }

    @Test
    public void noUserPresent() {
        ApplicationUser temp = applicationUserService.getUserById(0L);
        verify(applicationUserRepository).findById(0L);
        assertNull(temp);
    }

    @Test
    public void userIsPresent() {
        ApplicationUser temp = applicationUserService.getUserById(1L);
        verify(applicationUserRepository).findById(1L);
        assertNotNull(temp);
    }

    @Test
    public void createValidUser() {
        ApplicationUser temp = new ApplicationUser();
        temp.setUsername("TestUser");
        temp.setPassword("TestPassword");
        applicationUserService.createUser(temp);

        verify(applicationUserRepository).existsByUsername("TestUser");
        verify(applicationUserRepository).save(temp);
    }

    @Test(expected = UserAlreadyExistsAuthenticationException.class)
    public void createInvalidUser() {
        ApplicationUser temp = new ApplicationUser();
        temp.setUsername("ExampleUser");
        temp.setPassword("ExamplePassword");
        applicationUserService.createUser(temp);

        verify(applicationUserRepository).existsByUsername("ExampleUser");

    }
}
