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

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {

    private static final String PHOTO_RELATIVE_PATH = "../resources/images/";
    private static final double COUNT_OF_VALUES = 5.0;

    private RoomDao roomDao = DaoFactory.daoFactory.getRoomDao();
    private BookingDao bookingDao = DaoFactory.daoFactory.getBookingDao();

    public List<Room> findAll() throws ServiceException {
        try {
            return roomDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAllUsers in roomDao", e);
        }
    }

    public List<Room> getRoomsByPage(int page) throws ServiceException{
        if (page>0 && page <= getAmountOfPages()){
                int offset = (page - 1) * (int)COUNT_OF_VALUES;
            try {
                return roomDao.getRoomsByPage(offset);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return Collections.emptyList();
    }

    public int getAmountOfPages() throws ServiceException {
        try {
            int rowsCount = roomDao.getCountOfRows();
            return (int)Math.ceil(rowsCount/ COUNT_OF_VALUES);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Room findRoomById(long roomId) throws ServiceException {
        try {
            Optional<Room> room = roomDao.findRoomById(roomId);
            if (room.isPresent()) {
                return room.get();
            }else {
                throw new ServiceException("This room is not present");
            }
        } catch (DaoException e) {
            throw new ServiceException("Problem in method find room by id in roomDao", e);
        }
    }

    @Override
    public List<Room> findFreeRoomsByBooking(long bookingId) throws ServiceException {
        Optional<Booking> booking;
        try {
            booking = bookingDao.findBookingById(bookingId);
            if (booking.isPresent()) {
                return roomDao.findFreeRoomsByBooking(booking.get());
            } else {
                return Collections.emptyList();
            }
        } catch (DaoException e) {
            throw new ServiceException("Problem in method findAllUsers in roomDao", e);
        }
    }

    @Override
    public boolean createRoom(Room room) throws ServiceException {
        boolean isCommandSuccess = false;
        if (RoomValidator.isRoomValid(room)) {
            try {
                room.setPhotoPath(PHOTO_RELATIVE_PATH + room.getPhotoPath());
                roomDao.createRoom(room);
                isCommandSuccess = true;
            } catch (DaoException e) {
                throw new ServiceException("Problem in method create room in room dao", e);
            }
        }
        return isCommandSuccess;
    }

    @Override
    public boolean updateRoomPhoto(long roomId, String fileName) throws ServiceException {
        boolean isCommandSuccess = false;
            try {
                Room room;
                String newPath = PHOTO_RELATIVE_PATH + fileName;
                Optional<Room> roomOptional = roomDao.findRoomById(roomId);
                if (roomOptional.isPresent()) {
                    room = roomOptional.get();
                    roomDao.updateRoomPhoto(roomId,newPath);
                    String oldPath = room.getPhotoPath();
                    File fileToDelete = new File(oldPath);
                    isCommandSuccess = fileToDelete.delete();
                }
            } catch (DaoException e) {
                throw new ServiceException("Problem in method create room in room dao", e);
            }
        return isCommandSuccess;
    }

    @Override
    public boolean updateRoomInfo(Room room) throws ServiceException {
        boolean isCommandSuccess = false;
        if (RoomValidator.isRoomValid(room)) {
            try {
                roomDao.updateRoomInfo(room);
                isCommandSuccess = true;
            } catch (DaoException e) {
                throw new ServiceException("Problem in method create room in room dao", e);
            }
        }
        return isCommandSuccess;
    }

    @Override
    public void deleteRoom(String roomIdLine) throws ServiceException {
        long roomId = Long.parseLong(roomIdLine);
        try {
            roomDao.deleteRoomById(roomId);
        } catch (DaoException e) {
            throw new ServiceException("Problem in method delete room by Id in room dao", e);
        }
    }
}
