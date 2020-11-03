package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.dao.StatementMaster;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.enums.RoomGrade;
import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    private static final Logger LOGGER = LogManager.getLogger(RoomDaoImpl.class);

    private static final String INSERTED_COLUMNS = "rooms.name, rooms.number_of_rooms, rooms.floor, rooms.max_persons, " +
            "rooms.cost, rooms.has_Wifi, rooms.has_TV, rooms.has_bathroom, rooms.number_of_beds,rooms.room_description, rooms.photo_path, grades.grade_name";
    private static final String SELECTED_COLUMNS = "rooms.id, " + INSERTED_COLUMNS;
    private static final String FIND_ALL_ROOMS = "SELECT " + SELECTED_COLUMNS + " FROM rooms INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id";
    private static final String FIND_ROOMS_BY_BOOKING = "SELECT " + SELECTED_COLUMNS + " FROM rooms INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id WHERE INNER ";


    // SELECT hotel.rooms.id, hotel.rooms.name, hotel.rooms.number_of_rooms, hotel.rooms.floor, hotel.rooms.max_persons, hotel.rooms.cost, hotel.rooms.has_Wifi, hotel.rooms.has_TV, hotel.rooms.has_bathroom, hotel.rooms.number_of_beds, hotel.rooms.room_description, hotel.rooms.photo_path,
    // hotel.grades.grade_name FROM hotel.rooms INNER JOIN hotel.grades ON hotel.grades.id  = hotel.rooms.grade_id


    @Override
    public List<Room> findAll() throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByQuery(connection, FIND_ALL_ROOMS);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem in RoomDao, while trying to find all rooms", e);
        }
    }

    @Override
    public Room findRoomById(int id) throws DaoException {
        return null;
    }

    @Override
    public Room sortRoomByCost(double cost) throws DaoException {
        return null;
    }

    @Override
    public List<Room> findRoomsByBooking(Booking booking) throws DaoException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByPrStatement(proxyConnection, FIND_ROOMS_BY_BOOKING,
                    (preparedStatement, params) -> {
                        preparedStatement.setString(1, booking.getStartDate());
                                preparedStatement.setString(2, booking.getEndDate());
                                preparedStatement.setInt(3, booking.getNumberOfRooms());
                                preparedStatement.setInt(4, booking.getNumberOfBeds());
                                preparedStatement.setInt(5, booking.getMaxPersons());
                                preparedStatement.setBoolean(5, booking.isHasWifi());
                                preparedStatement.setBoolean(5, booking.isHasTV());
                                preparedStatement.setBoolean(5, booking.isHasBathroom());
                    },booking);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem in UserDao, while trying to fina all users", e);
        }
    }

    @Override
    protected Room makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String roomName = rs.getString(2);
        int numberOfRooms = rs.getInt(3);
        int floor = rs.getInt(4);
        int maxPersons = rs.getInt(5);
        double cost = rs.getDouble(6);
        boolean hasWifi = rs.getBoolean(7);
        boolean hasTV = rs.getBoolean(8);
        boolean hasBathroom = rs.getBoolean(9);
        int numberOfBeds = rs.getInt(10);
        String description = rs.getString(11);
        String photoPath = rs.getString(12);
        RoomGrade gradeName = RoomGrade.valueOf(rs.getString(13).toUpperCase());
        return new Room(id, roomName, numberOfRooms, floor, maxPersons, cost, hasWifi, hasTV, hasBathroom, numberOfBeds, description, photoPath, gradeName);
    }

    @Override
    public Room sortRoomsByGrade() throws DaoException {
        return null;
    }

    @Override
    public Room sortRoomsByPeopleCount() throws DaoException {
        return null;
    }


}
