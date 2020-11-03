package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.DaoException;

import java.util.List;

public interface BookingDao {

    List<Booking> findBookingsByUserId(int id) throws DaoException;

    void changeBookingStatusById(int bookingId, int statusId) throws DaoException;

    void createBooking(Booking booking) throws DaoException;

    void addRoomToBooking(int bookingsId, int roomId) throws DaoException;

    List<Booking> findAll() throws DaoException;

}
