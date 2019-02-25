package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.ForeignUserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.UserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.Exceptions.UserNotFoundException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Services.ProPayService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class UserDetailServiceTest {

    private ApplicationUser user = new ApplicationUser();
    private MockApplicationUserService mockApplicationUserService = new MockApplicationUserService(user);
    private MockUserTransactionService mockUserTransactionService = new MockUserTransactionService();
    private ProPayService mockPropay;

    @Before
    public void setup() {
        mockPropay = mock(ProPayService.class);
        user.setUsername("dude");
        user.setPassword("password");
        user.setEmail("dude@dude.dude");
        user.setId(-1);
    }

    @Test
    public void getUserDetailServiceTest_One() {
        UserDetailService service = new UserDetailService(mockApplicationUserService, mockUserTransactionService, mockPropay);

        UserDetailResponse response = service.getUserDetails(null);

        Assert.assertEquals(response.getUsername(), "dude");
        Assert.assertEquals(response.getCompletedTransactions().size(), 1);
        Assert.assertEquals(response.getEmail(), "dude@dude.dude");
        Assert.assertEquals(response.getAccountBalance(), 0, 0.0001);
    }

    @Test
    public void getForeignUserDetailServiceTest_One() throws UserNotFoundException {
        UserDetailService service = new UserDetailService(mockApplicationUserService, mockUserTransactionService, null);

        ForeignUserDetailResponse response = service.getForeignUserDetails("dude");

        Assert.assertEquals(response.getUsername(), "dude");
        Assert.assertEquals(response.getCompletedTransactions().size(), 1);
    }

    @Test(expected = UserNotFoundException.class)
    public void getForeignUserDetailServiceTest_NotFound() throws UserNotFoundException {
        UserDetailService service = new UserDetailService(mockApplicationUserService, mockUserTransactionService, null);

        ForeignUserDetailResponse response = service.getForeignUserDetails("no dude");
    }


}
