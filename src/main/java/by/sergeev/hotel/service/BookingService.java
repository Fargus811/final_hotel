package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Booking service.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public interface BookingService {

    /**
     * Find booking by id optional.
     *
     * @param bookingId the booking id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Booking> findBookingById(long bookingId) throws ServiceException;

    /**
     * Find bookings by user id list.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Booking> findBookingsByUserId(long userId) throws ServiceException;

    /**
     * Add room to booking.
     *
     * @param bookingId the booking id
     * @param roomId    the room id
     * @param totalCost the total cost
     * @throws ServiceException the service exception
     */
    void addRoomToBooking(long bookingId, long roomId, BigDecimal totalCost) throws ServiceException;

    /**
     * Gets total cost of booking.
     *
     * @param bookingId the booking id
     * @param roomId    the room id
     * @return the total cost of booking
     * @throws ServiceException the service exception
     */
    BigDecimal getTotalCostOfBooking(long bookingId, long roomId) throws ServiceException;

    /**
     * Change booking status by id.
     *
     * @param bookingId     the booking id
     * @param bookingStatus the booking status
     * @throws ServiceException the service exception
     */
    void changeBookingStatusById(long bookingId, int bookingStatus) throws ServiceException;

    /**
     * Create booking boolean.
     *
     * @param freshBooking the fresh booking
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createBooking(Booking freshBooking) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Booking> findAll() throws ServiceException;

    /**
     * Pay for booking boolean.
     *
     * @param userId    the user id
     * @param bookingId the booking id
     * @param totalCost the total cost
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean payForBooking(long userId, long bookingId, BigDecimal totalCost) throws ServiceException;

    /**
     * Delete room from booking boolean.
     *
     * @param bookingId the booking id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteRoomFromBooking(long bookingId) throws ServiceException;
}
