package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookingDao {

    List<Booking> findBookingsByUserId(int userId) throws DaoException;

    Optional<Booking> findBookingById (int bookingId) throws DaoException;

    void changeBookingStatusById(int bookingId, int statusId) throws DaoException;

    void createBooking(Booking booking) throws DaoException;

    void addRoomToBooking(int bookingsId, int roomId, BigDecimal bigDecimal) throws DaoException;

    List<Booking> findAll() throws DaoException;

}
