package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookingDao {

    List<Booking> findBookingsByUserId(long userId) throws DaoException;

    Optional<Booking> findBookingById (long bookingId) throws DaoException;

    void changeBookingStatusById(long bookingId, int statusId) throws DaoException;

    void createBooking(Booking booking) throws DaoException;

    void addRoomToBooking(long bookingsId, long roomId, BigDecimal bigDecimal) throws DaoException;

    List<Booking> findAll() throws DaoException;

    boolean changeBookingStatusForPayment(ProxyConnection proxyConnection, long bookingId) throws DaoException;

    boolean deleteRoomFromBooking(long bookingId) throws DaoException;
}
