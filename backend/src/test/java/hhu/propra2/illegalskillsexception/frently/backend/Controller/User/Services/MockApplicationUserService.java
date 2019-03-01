package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices.IApplicationUserService;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import org.springframework.security.core.Authentication;

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
    public void encryptPassword(ApplicationUser user) {

    }

    @Override
    public ApplicationUser getCurrentUser(Authentication authentication) {
        return this.userIntern;
    }
}
