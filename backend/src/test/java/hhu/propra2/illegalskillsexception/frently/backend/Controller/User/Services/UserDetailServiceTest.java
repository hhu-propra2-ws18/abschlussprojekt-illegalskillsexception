package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.ForeignUserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.UserDetailResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDetailServiceTest {

    private ApplicationUser user = new ApplicationUser();
    private MockApplicationUserService mockApplicationUserService = new MockApplicationUserService(user);
    private MockUserTransactionService mockUserTransactionService = new MockUserTransactionService();

    @Before
    public void setup() {
        user.setUsername("dude");
        user.setPassword("password");
        user.setEmail("dude@dude.dude");
        user.setId(-1);
    }

    @Test
    public void getUserDetailServiceTest_One() {
        UserDetailService service = new UserDetailService(mockApplicationUserService, mockUserTransactionService);

        UserDetailResponse response = service.getUserDetails(null);

        Assert.assertEquals(response.getUsername(), "dude");
        Assert.assertEquals(response.getCompletedTransactions().size(), 1);
        Assert.assertEquals(response.getEmail(), "dude@dude.dude");
        Assert.assertEquals(response.getAccountBalance(), 0, 0.0001);
    }

    @Test
    public void getForeignUserDetailServiceTest_One() throws UserNotFoundException {
        UserDetailService service = new UserDetailService(mockApplicationUserService, mockUserTransactionService);

        ForeignUserDetailResponse response = service.getForeignUserDetails("dude");

        Assert.assertEquals(response.getUsername(), "dude");
        Assert.assertEquals(response.getCompletedTransactions().size(), 1);
    }

    @Test(expected = UserNotFoundException.class)
    public void getForeignUserDetailServiceTest_NotFound() throws UserNotFoundException {
        UserDetailService service = new UserDetailService(mockApplicationUserService, mockUserTransactionService);

        ForeignUserDetailResponse response = service.getForeignUserDetails("no dude");
    }


}
