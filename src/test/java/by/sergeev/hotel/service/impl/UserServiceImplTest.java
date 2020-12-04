package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.impl.UserDaoImpl;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import org.mockito.Mockito;
import org.powermock.reflect.internal.WhiteboxImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class UserServiceImplTest {

    UserServiceImpl userService;
    UserDaoImpl userDao;
    List<User> expected;

    @BeforeMethod
    public void setUp() {
        userDao = Mockito.mock(UserDaoImpl.class);
        userService = new UserServiceImpl();
        WhiteboxImpl.setInternalState(userService, "userDao", userDao);
        expected = new ArrayList<>();
    }

    @Test
    public void testFindAllUsers() {
        try {
            when(userDao.findAllUsers()).thenReturn(expected);
            List<User> actual = userService.findAllUsers();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testCheckIsValid() {
    }

    @Test
    public void testCheckIsEmailFree() {
    }

    @Test
    public void testRegister() {
    }

    @Test
    public void testLogIn() {
    }

    @Test
    public void testAddBalance() {
    }

    @Test
    public void testFindUserById() {
    }

    @Test
    public void testUpdateUser() {
    }

    @Test
    public void testUpdateUserPassword() {
    }

    @Test
    public void testDeleteAccount() {
    }

    @Test
    public void testChangeAccountStatus() {
    }

    @Test
    public void testActivateAccount() {
    }
}
