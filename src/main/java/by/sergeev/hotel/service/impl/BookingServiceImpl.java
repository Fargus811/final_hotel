package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.BookingDao;
import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
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

    @Override
    public List<Booking> findBookingsByUserId(long userId) throws ServiceException {
        try {
            return bookingDao.findBookingsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findBookingsByUserId in bookingDao", e);
        }
    }

    @Override
    public void changeBookingStatusById(long bookingId, String bookingStatus) throws ServiceException {
        int statusId = BookingStatus.valueOf(bookingStatus).ordinal();
        try {
            bookingDao.changeBookingStatusById(bookingId, statusId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method changeBookingsByUserId in bookingDao", e);
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
                throw new ServiceException("Problem in method createBooking in bookingDao", e);
            }
        }
        return isCommandSuccess;
    }

    @Override
    public void addRoomToBooking(long bookingId, long roomId, BigDecimal totalCost) throws ServiceException {
        try {
            bookingDao.addRoomToBooking(bookingId, roomId, totalCost);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method addRoomToBooking in bookingDao", e);
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
            throw new ServiceException("Problem in method findAllUsers in bookingDao", e);
        }
    }
}
