package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.BookingDao;
import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.impl.BookingDaoImpl;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.powermock.reflect.Whitebox;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({BookingDaoImpl.class})
public class BookingServiceImplTest {

    BookingDao bookingDao;
    BookingServiceImpl bookingService;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        bookingDao = Mockito.mock(BookingDao.class);
        bookingService = new BookingServiceImpl();

    }

    @Test
    public void testFindBookingById() {
        try {
            Optional<Booking> expected = Optional.of(new Booking());
            when(bookingDao.findBookingById(any(Long.class))).thenReturn(expected);
            Optional<Booking> actual = bookingService.findBookingById(1);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testFindBookingsByUserId() {
    }

    @Test
    public void testChangeBookingStatusById() {
    }

    @Test
    public void testCreateBooking() {
    }

    @Test
    public void testAddRoomToBooking() {
    }

    @Test
    public void testGetTotalCostOfBooking() {
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testPayForBooking() {
    }

    @Test
    public void testDeleteRoomFromBooking() {
    }
}
