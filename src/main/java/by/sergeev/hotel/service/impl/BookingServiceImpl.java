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
import by.sergeev.hotel.validator.UserFormValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookingServiceImpl implements BookingService {

    private BookingDao bookingDao = DaoFactory.daoFactory.getBookingDao();
    private RoomDao roomDao = DaoFactory.daoFactory.getRoomDao();

    @Override
    public List<Booking> findBookingsByUserId(int userId) throws ServiceException {
        try {
            return bookingDao.findBookingsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findBookingsByUserId in bookingDao", e);
        }
    }

    @Override
    public void changeBookingStatusById(int bookingId, String bookingStatus) throws ServiceException {
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
                isCommandSuccess =true;
            } catch (DaoException e) {
                throw new ServiceException("Problem in method createBooking in bookingDao", e);
            }
        }
        return isCommandSuccess;
    }

    @Override
    public void addRoomToBooking(int bookingId, int roomId) throws ServiceException {
        try {
            BigDecimal totalСost = getTotalCostOfBooking(bookingId, roomId);
            bookingDao.addRoomToBooking(bookingId, roomId, totalСost);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method addRoomToBooking in bookingDao", e);
        }
    }

    private BigDecimal getTotalCostOfBooking(int bookingId, int roomId) throws DaoException {
        Optional<Booking> booking = bookingDao.findBookingById(bookingId);
        Optional<Room> room = roomDao.findRoomById(roomId);
        BigDecimal totalСost = BigDecimal.ZERO;
        if (booking.isPresent() & room.isPresent()){
            String startDate = booking.get().getStartDate();
            String endDate = booking.get().getEndDate();
            LocalDate dateBefore = LocalDate.parse(startDate) ;
            LocalDate dateAfter = LocalDate.parse(endDate);
            long daysBetween =DAYS.between(dateBefore, dateAfter);
            totalСost = room.get().getCost().multiply(BigDecimal.valueOf(daysBetween));
        }
        return totalСost;
    }

    @Override
    public List<Booking> findAll() throws ServiceException {
        try {
            return bookingDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAll in bookingDao", e);
        }
    }
}
