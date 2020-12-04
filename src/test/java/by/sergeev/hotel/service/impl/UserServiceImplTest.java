package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.impl.UserDaoImpl;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import org.mockito.Mockito;
import org.powermock.reflect.internal.WhiteboxImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

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
    public void testCheckIsEmailFree() {
        try {
            Optional<User> user = Optional.of(new User());
            when(userDao.findUserByEmail(any(String.class))).thenReturn(user);
            boolean actual = userService.checkIsEmailFree("email");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testRegister() {
        try {
            User user = new User();
            String email = "email";
            user.setEmail(email);
            String firstName = "Daniil";
            user.setFirstName(firstName);
            String lastName = "Sergeev";
            user.setLastName(lastName);
            String password = "$2fc@";
            doNothing().when(userDao).create(user, password);
            userService.register(email, password, firstName, lastName);
            verify(userDao);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }


    @Test
    public void testAddBalance() {
        try {
            doNothing().when(userDao).updateUserBalance(1, BigDecimal.valueOf(1000));
            userService.addBalance(1,BigDecimal.TEN);
            verify(userDao);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFindUserById() {
        try {
            Optional<User> expected = Optional.of(new User());
            when(userDao.findEntityById(any(Long.class))).thenReturn(expected);
            Optional<User> actual = userService.findUserById(0);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testUpdateUser() {
        try {
            User user = new User();
            String email = "email";
            user.setEmail(email);
            String firstName = "Daniil";
            user.setFirstName(firstName);
            String lastName = "Sergeev";
            user.setLastName(lastName);
            doNothing().when(userDao).updateUserInformation(user);
            userService.updateUser(user);
            verify(userDao);
        }catch (ServiceException | DaoException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testChangeAccountStatus() {
        try {
            doNothing().when(userDao).changeAccountStatus(1, 1);
            userService.changeAccountStatus(1,1);
            verify(userDao);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testActivateAccount() {
        try {
            User user = new User();
            user.setId(1);
            doNothing().when(userDao).changeAccountStatus(1, 1);
            when(userDao.findUserByEmail(anyString())).thenReturn(Optional.of(user));
            userService.activateAccount("email");
            verify(userDao);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }
}
