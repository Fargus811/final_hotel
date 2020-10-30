package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.exception.ServiceException;

import java.util.List;

public interface BookingService {

    List<Booking> findBookingsByUserId(int id) throws ServiceException;

    void changeBookingStatusById(int bookingId, String bookingStatus) throws ServiceException;

    void createBooking(Booking freshBooking) throws ServiceException;
}
