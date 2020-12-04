package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.impl.BookingDaoImpl;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import org.mockito.Mockito;
import org.powermock.reflect.internal.WhiteboxImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class BookingServiceImplTest {

    BookingServiceImpl bookingService;
    BookingDaoImpl bookingDao;
    List<Booking> expected;

    @BeforeMethod
    public void setUp() {
        bookingDao = Mockito.mock(BookingDaoImpl.class);
        bookingService = new BookingServiceImpl();
        WhiteboxImpl.setInternalState(bookingService, "bookingDao", bookingDao);
        expected = new ArrayList<>();
    }

    @Test
    public void testFindBookingById() {
        try {
            Optional<Booking> expected = Optional.of(new Booking());
            when(bookingDao.findBookingById(any(Long.class))).thenReturn(expected);
            Optional<Booking> actual = bookingService.findBookingById(0);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testFindBookingsByUserId() {
        List<Booking> listBookingsTest = new ArrayList<>();
        try {
            when(bookingDao.findBookingsByUserId(any(Long.class))).thenReturn(listBookingsTest);
            List<Booking> actualListBookings = bookingService.findBookingsByUserId(1);
            assertEquals(actualListBookings, listBookingsTest);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }

    }

    @Test
    public void testChangeBookingStatusById() {
        try {
            int testId = 1;
            doNothing().when(bookingDao).changeBookingStatusById(testId, testId);
            bookingService.changeBookingStatusById(testId, testId);
            verify(bookingDao).changeBookingStatusById(testId, testId);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateBooking() {
        try {
            Booking freshBooking = new Booking();
            freshBooking.setStartDate("2020-12-07");
            freshBooking.setEndDate("2020-12-17");
            doNothing().when(bookingDao).createBooking(freshBooking);
            bookingService.createBooking(freshBooking);
            verify(bookingDao).createBooking(freshBooking);
        } catch (ServiceException |DaoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddRoomToBooking() {
        try {
            int testId = 1;
            doNothing().when(bookingDao).addRoomToBooking(testId, testId, BigDecimal.ONE);
            bookingService.addRoomToBooking(testId, testId,BigDecimal.ONE);
            verify(bookingDao).addRoomToBooking(testId,testId,BigDecimal.ONE);
        } catch (ServiceException | DaoException e) {
            fail(e.getMessage(), e);
        }
    }

    @Test
    public void testFindAll() {
        try {
            when(bookingDao.findAll()).thenReturn(expected);
            List<Booking> actual = bookingService.findAll();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testDeleteRoomFromBooking() {
        try {
            when(bookingDao.deleteRoomFromBooking(anyLong())).thenReturn(true);
            boolean expected = bookingService.deleteRoomFromBooking(1);
        }catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }
    @AfterClass
    public void tierDown() {
        bookingDao = null;
        bookingService = null;
        expected = null;
    }
}
