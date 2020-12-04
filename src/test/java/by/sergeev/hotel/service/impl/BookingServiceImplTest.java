package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.impl.BookingDaoImpl;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import org.mockito.Mockito;
import org.powermock.reflect.internal.WhiteboxImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class BookingServiceImplTest {

    BookingServiceImpl bookingService;
    BookingDaoImpl mock;
    List<Booking> expected;

    @BeforeMethod
    public void setUp() {
        mock = Mockito.mock(BookingDaoImpl.class);
        bookingService = new BookingServiceImpl();
        WhiteboxImpl.setInternalState(bookingService, "bookingDao", mock);
        expected = new ArrayList<>();
    }

    @Test
    public void testFindBookingById() {
        try {
            Optional<Booking> expected = Optional.of(new Booking());
            when(mock.findBookingById(any(Long.class))).thenReturn(expected);
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
            when(mock.findBookingsByUserId(any(Long.class))).thenReturn(listBookingsTest);
            List<Booking> actualListBookings = bookingService.findBookingsByUserId(1);
            assertEquals(actualListBookings, listBookingsTest);
        } catch (DaoException | ServiceException exp) {
            fail(exp.getMessage());
        }

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
        try {
            when(mock.findAll()).thenReturn(expected);
            List<Booking> actual = bookingService.findAll();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testPayForBooking() {
    }

    @Test
    public void testDeleteRoomFromBooking() {
    }
}
