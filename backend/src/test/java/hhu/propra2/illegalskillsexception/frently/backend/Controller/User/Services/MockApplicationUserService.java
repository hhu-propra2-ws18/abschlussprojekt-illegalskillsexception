package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Services.IApplicationUserService;
import org.springframework.security.core.Authentication;

import java.util.List;

public class MockApplicationUserService implements IApplicationUserService {

    private final ApplicationUser userIntern;

    public MockApplicationUserService(ApplicationUser userIntern) {
        this.userIntern = userIntern;
    }

    @Override
    public ApplicationUser getApplicationUserByUsername(String userName) {
        if (userName.equals(userIntern.getUsername())) {
            return userIntern;
        }
        return null;
    }

    @Override
    public void createUser(ApplicationUser user) {

    }

    @Override
    public ApplicationUser getUserById(Long userId) {
        return null;
    }

    @Override
    public List<ApplicationUser> getAllUsers() {
        return null;
    }

    @Override
    public ApplicationUser updateUser(ApplicationUser updateUser) {
        return null;
    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public void encryptPassword(ApplicationUser user) {

    }

    @Override
    public ApplicationUser getCurrentUser(Authentication authentication) {
        return this.userIntern;
    }
}
