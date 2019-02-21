package hhu.propra2.illegalskillsexception.frently.backend.Controller.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IApplicationUserService {
    ApplicationUser getApplicationUserByUsername(String userName);

    void createUser(ApplicationUser user);

    ApplicationUser getUserById(Long userId);

    List<ApplicationUser> getAllUsers();

    ApplicationUser updateUser(ApplicationUser updateUser);

    void deleteUser(long userId);

    void encryptPassword(ApplicationUser user);

    ApplicationUser getCurrentUser(Authentication authentication);
}
