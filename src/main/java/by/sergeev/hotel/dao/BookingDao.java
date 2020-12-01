package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Booking dao.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public interface BookingDao {

    /**
     * Find bookings by user id list.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Booking> findBookingsByUserId(long userId) throws DaoException;

    /**
     * Find booking by id optional.
     *
     * @param bookingId the booking id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Booking> findBookingById (long bookingId) throws DaoException;

    /**
     * Change booking status by id.
     *
     * @param bookingId the booking id
     * @param statusId  the status id
     * @throws DaoException the dao exception
     */
    void changeBookingStatusById(long bookingId, int statusId) throws DaoException;

    /**
     * Create booking.
     *
     * @param booking the booking
     * @throws DaoException the dao exception
     */
    void createBooking(Booking booking) throws DaoException;

    /**
     * Add room to booking.
     *
     * @param bookingsId the bookings id
     * @param roomId     the room id
     * @param bigDecimal the big decimal
     * @throws DaoException the dao exception
     */
    void addRoomToBooking(long bookingsId, long roomId, BigDecimal bigDecimal) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Booking> findAll() throws DaoException;

    /**
     * Change booking status for payment boolean.
     *
     * @param proxyConnection the proxy connection
     * @param bookingId       the booking id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean changeBookingStatusForPayment(ProxyConnection proxyConnection, long bookingId) throws DaoException;

    /**
     * Delete room from booking boolean.
     *
     * @param bookingId the booking id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteRoomFromBooking(long bookingId) throws DaoException;
}
