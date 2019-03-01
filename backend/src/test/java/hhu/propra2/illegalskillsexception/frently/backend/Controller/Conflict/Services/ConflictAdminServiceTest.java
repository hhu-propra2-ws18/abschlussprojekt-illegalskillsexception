package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Security.Exceptions.NotAdminException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ConflictAdminServiceTest {

    private ConflictAdminService conflictAdminService;
    private Role role1 = new Role();
    private Role role2 = new Role();

    @Before
    public void setup() {
        conflictAdminService = new ConflictAdminService();
    }

    @Test(expected = NotAdminException.class)
    public void isNotAdmin() throws Exception{
        role1.setName("user");
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        conflictAdminService.isAdmin(roles);
    }
    @Test
    public void isAdmin() throws Exception {
        role2.setName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role2);
        conflictAdminService.isAdmin(roles);
    }
}