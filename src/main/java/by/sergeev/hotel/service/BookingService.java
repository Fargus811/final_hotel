package by.sergeev.hotel.service;

import by.sergeev.hotel.entity.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> findBookingsByUserId(int id);

    void changeBookingStatusById(int bookingId, String bookingStatus);

    void createBooking(Booking freshBooking);
}
