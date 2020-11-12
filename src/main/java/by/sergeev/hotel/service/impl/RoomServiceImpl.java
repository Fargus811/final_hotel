package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.BookingDao;
import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.exception.ServiceException;
import by.sergeev.hotel.service.RoomService;
import by.sergeev.hotel.validator.RoomValidator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {

    private static final String PHOTO_RELATIVE_PATH = "../resources/images/";

    private RoomDao roomDao = DaoFactory.daoFactory.getRoomDao();
    private BookingDao bookingDao = DaoFactory.daoFactory.getBookingDao();

    public List<Room> findAll() throws ServiceException {
        try {
            return roomDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAll in roomDao", e);
        }
    }

    @Override
    public List<Room> findFreeRoomsByBooking(int bookingId) throws ServiceException {
        Optional<Booking> booking;
        try {
            booking = bookingDao.findBookingById(bookingId);
            if (booking.isPresent()) {
                return roomDao.findFreeRoomsByBooking(booking.get());
            } else {
                return Collections.emptyList();
            }
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAll in roomDao", e);
        }
    }

    @Override
    public boolean createRoom(Room room) throws ServiceException {
        boolean isCommandSuccess = false;
        if (RoomValidator.isRoomValid(room)) {
            try {
                room.setPhotoPath(PHOTO_RELATIVE_PATH +room.getPhotoPath());
                roomDao.createRoom(room);
                isCommandSuccess = true;
            } catch (DaoException e) {
                throw new ServiceException("Problem in method create room in room dao", e);
            }
        }
        return isCommandSuccess;
    }

    @Override
    public void deleteRoom(String roomIdLine) throws ServiceException{
        int roomId = Integer.parseInt(roomIdLine);
        try {
            roomDao.deleteRoomById(roomId);
        }catch (DaoException e){
            throw new ServiceException("Problem in method delete room by Id in room dao", e);
        }
    }
}
