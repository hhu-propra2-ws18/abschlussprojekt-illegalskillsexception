package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class ApplicationUserServiceTest {

    private ApplicationUserService userService = new ApplicationUserService(null,new BCryptPasswordEncoder( ),null);

    @Test
    public void encryptPassword(){
        ApplicationUser user = new ApplicationUser();
        user.setUsername("some user");
        user.setPassword("password");

        userService.encryptPassword(user);

        assertNotEquals(new BCryptPasswordEncoder().encode("password"),user.getPassword());
    }



}