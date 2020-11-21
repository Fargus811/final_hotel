package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface BookingService {

    List<Booking> findBookingsByUserId(long userId) throws ServiceException;

    void addRoomToBooking(long bookingId, long roomId, BigDecimal totalCost) throws ServiceException;

    BigDecimal getTotalCostOfBooking(long bookingId, long roomId) throws ServiceException;

    void changeBookingStatusById(long bookingId, String bookingStatus) throws ServiceException;

    boolean createBooking(Booking freshBooking) throws ServiceException;

    List<Booking> findAll() throws ServiceException;
}
