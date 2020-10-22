package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.BookingDao;
import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.enums.BookingStatus;
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
        return bookingDao.findBookingsByUserId(userId);
    }

    @Override
    public void changeBookingStatusById(int bookingId, String bookingStatus) {
        int statusId = BookingStatus.valueOf(bookingStatus).getId();
        bookingDao.changeBookingStatusById(bookingId, statusId);
    }

    @Override
    public void createBooking(Booking freshBooking) throws ServiceException {
        bookingDao.createBooking(freshBooking);
    }
}
