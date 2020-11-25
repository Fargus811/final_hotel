package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.*;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.User;
import by.sergeev.hotel.entity.enums.BookingStatus;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import by.sergeev.hotel.validator.DateValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookingServiceImpl implements BookingService {

    private BookingDao bookingDao = DaoFactory.daoFactory.getBookingDao();
    private RoomDao roomDao = DaoFactory.daoFactory.getRoomDao();
    private UserDao userDao = DaoFactory.daoFactory.getUserDao();

    @Override
    public Optional<Booking> findBookingById(long bookingId) throws ServiceException {
        try {
            return bookingDao.findBookingById(bookingId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findBooking by id in booking service", e);
        }
    }

    @Override
    public List<Booking> findBookingsByUserId(long userId) throws ServiceException {
        try {
            return bookingDao.findBookingsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findBookingsByUserId in booking service", e);
        }
    }

    @Override
    public void changeBookingStatusById(long bookingId, int bookingStatus) throws ServiceException {
        try {
            bookingDao.changeBookingStatusById(bookingId, bookingStatus);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method changeBookingsByUserId in booking service", e);
        }
    }


    @Override
    public boolean createBooking(Booking freshBooking) throws ServiceException {
        boolean isCommandSuccess = false;
        if (DateValidator.isValidStartAndEndDate(freshBooking.getStartDate(), freshBooking.getEndDate())) {
            try {
                bookingDao.createBooking(freshBooking);
                isCommandSuccess = true;
            } catch (DaoException e) {
                throw new ServiceException("Problem in method createBooking in booking service", e);
            }
        }
        return isCommandSuccess;
    }

    @Override
    public void addRoomToBooking(long bookingId, long roomId, BigDecimal totalCost) throws ServiceException {
        try {
            bookingDao.addRoomToBooking(bookingId, roomId, totalCost);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method addRoomToBooking in booking service", e);
        }
    }

    public BigDecimal getTotalCostOfBooking(long bookingId, long roomId) throws ServiceException {
        Optional<Booking> booking;
        Optional<Room> room;
        try {
            booking = bookingDao.findBookingById(bookingId);
            room = roomDao.findRoomById(roomId);
        } catch (DaoException e) {
            throw new ServiceException("Problem with getting total cost in booking service", e);
        }
        BigDecimal totalCost = BigDecimal.ZERO;
        if (booking.isPresent() && room.isPresent()) {
            String startDate = booking.get().getStartDate();
            String endDate = booking.get().getEndDate();
            LocalDate dateBefore = LocalDate.parse(startDate);
            LocalDate dateAfter = LocalDate.parse(endDate);
            long daysBetween = DAYS.between(dateBefore, dateAfter);
            totalCost = room.get().getCost().multiply(BigDecimal.valueOf(daysBetween));
        }
        return totalCost;
    }

    @Override
    public List<Booking> findAll() throws ServiceException {
        try {
            return bookingDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAllUsers in booking service", e);
        }
    }

    @Override
    public boolean payForBooking(long userId, long bookingId, BigDecimal totalCost) throws ServiceException {
        boolean isCommandSuccess = false;
        try {
            Optional<User> user = userDao.findEntityById(userId);
            BigDecimal balance = user.get().getBalance();
            BigDecimal totalBalance = balance.subtract(totalCost);
            if (totalBalance.compareTo(BigDecimal.ZERO) >= 0) {
                isCommandSuccess = TransactionManager.getInstance().payForBooking(userId, bookingId, totalBalance);
            }
        } catch (DaoException e) {
            throw new ServiceException("Problem with pay for booking in booking service", e);
        }
        return isCommandSuccess;
    }

    @Override
    public boolean deleteRoomFromBooking(long bookingId) throws ServiceException {
        try {
            return bookingDao.deleteRoomFromBooking(bookingId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method deleteRoomFromBooking in booking service", e);
        }
    }
}
