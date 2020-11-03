package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.BookingDao;
import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.enums.BookingStatus;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.BookingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LogManager.getLogger(BookingServiceImpl.class);

    private BookingDao bookingDao = DaoFactory.daoFactory.getBookingDao();

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
    public void createBooking(Booking freshBooking) throws ServiceException {
        try {
            bookingDao.createBooking(freshBooking);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method createBooking in bookingDao", e);
        }
    }

    @Override
    public void addRoomToBooking(int bookingId, int roomId) throws ServiceException {
        try {
            bookingDao.addRoomToBooking(bookingId,roomId);
        }catch (DaoException e){
            throw new ServiceException("Problem in method addRoomToBooking in bookingDao", e);
        }
    }

    @Override
    public List<Booking> findAll() throws ServiceException {
        try {
            return bookingDao.findAll();
        }catch (DaoException e){
            throw new ServiceException("Problem in method findAll in bookingDao", e);
        }
    }
}
