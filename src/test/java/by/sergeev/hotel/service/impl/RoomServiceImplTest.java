package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.impl.BookingDaoImpl;
import by.sergeev.hotel.dao.impl.RoomDaoImpl;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.enumeration.RoomGrade;
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
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class RoomServiceImplTest {

    RoomServiceImpl roomService;
    RoomDaoImpl roomDao;
    BookingDaoImpl bookingDao;
    List<Room> expected;

    @BeforeMethod
    public void setUp() {
        roomDao = Mockito.mock(RoomDaoImpl.class);
        bookingDao = Mockito.mock(BookingDaoImpl.class);
        roomService = new RoomServiceImpl();
        WhiteboxImpl.setInternalState(roomService, "roomDao", roomDao);
        WhiteboxImpl.setInternalState(roomService, "bookingDao", bookingDao);
        expected = new ArrayList<>();
    }

    @Test
    public void testFindAll() {
        try {
            when(roomDao.findAll()).thenReturn(expected);
            List<Room> actual = roomService.findAll();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testGetRoomsByPage() {
        try {
            when(roomDao.findAll()).thenReturn(expected);
            List<Room> actual = roomService.findAll();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testGetAmountOfPages() {
        try {
            when(roomDao.getCountOfRows()).thenReturn(10);
            int expected = 2;
            int actual = roomService.getAmountOfPages();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }

    }

    @Test
    public void testFindRoomById() {
        try {
            Optional<Room> expected = Optional.of(new Room());
            when(roomDao.findRoomById(any(Long.class))).thenReturn(expected);
            Optional<Room> actual = roomService.findRoomById(0);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testFindFreeRoomsByBooking() {
        try {
            when(bookingDao.findBookingById(any(Long.class))).thenReturn(Optional.of(new Booking()));
            when(roomDao.findFreeRoomsByBooking(any(Booking.class))).thenReturn(expected);
            List<Room> actual = roomService.findFreeRoomsByBooking(0);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testCreateRoom() {
        try {
            Room room = new Room();
            room.setName("Люкс");
            room.setDescription("Лучший");
            room.setPhotoPath("standard");
            room.setCost(BigDecimal.valueOf(300));
            room.setMaxPersons(4);
            room.setNumberOfBeds(2);
            room.setNumberOfRooms(2);
            room.setRoomGrade(RoomGrade.values()[1]);
            room.setHasWifi(true);
            room.setHasTV(true);
            room.setHasBathroom(false);
            doNothing().when(roomDao).createRoom(room);
            roomService.createRoom(room);
            verify(roomDao).createRoom(room);
        } catch (ServiceException |DaoException e) {
            fail(e.getMessage());
        }
    }
}
