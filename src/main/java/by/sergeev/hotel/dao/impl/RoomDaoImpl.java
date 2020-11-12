package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.enums.RoomGrade;
import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    private static final Logger LOGGER = LogManager.getLogger(RoomDaoImpl.class);

    private static final String INSERTED_COLUMNS = "rooms.name, rooms.number_of_rooms, rooms.max_persons, " +
            "rooms.cost, rooms.has_Wifi, rooms.has_TV, rooms.has_bathroom, rooms.number_of_beds,rooms.room_description, rooms.photo_path, grades.grade_name";
    private static final String SELECTED_COLUMNS = "rooms.id, " + INSERTED_COLUMNS;
    private static final String CREATE_ROOM = "INSERT INTO rooms ( rooms.name, rooms.number_of_rooms, rooms.max_persons, " +
            "rooms.cost, rooms.has_Wifi, rooms.has_TV, rooms.has_bathroom, rooms.number_of_beds, rooms.room_description," +
            " rooms.photo_path, rooms.grade_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_ROOMS = "SELECT " + SELECTED_COLUMNS + " FROM rooms INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id";
    private static final String FIND_ROOM_BY_ID = "SELECT " + SELECTED_COLUMNS + " FROM rooms INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id WHERE rooms.id = ?";
    private static final String DELETE_ROOM = "DELETE FROM rooms WHERE id = ? ";
    private static final String FIND_FREE_ROOMS_BY_BOOKING = "SELECT " + SELECTED_COLUMNS + " FROM rooms r INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id WHERE rooms.number_of_rooms = ? AND rooms.number_of_beds = ? AND rooms.max_persons = ? " +
            "AND rooms.has_Wifi = ? AND rooms.has_TV = ? AND room.has_bathroom = ? " +
            "AND NOT EXISTS (" +
            "    SELECT 1 FROM bookings WHERE room_id = r.id AND " +
            "  (startDate BETWEEN ? AND ? OR endDate BETWEEN ? AND ? OR ? BETWEEN startDate AND endDate)";


    @Override
    public List<Room> findAll() throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByQuery(connection, FIND_ALL_ROOMS);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem in RoomDao, while trying to find all rooms", e);
        }
    }

    @Override
    public Optional<Room> findRoomById(int roomId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return Optional.ofNullable(tryFindEntityByPrStatement(connection, FIND_ROOM_BY_ID,
                    ((preparedStatement, params) -> preparedStatement.setInt(1, roomId)), roomId));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem with bookingDao to find Bookings by userID", e);
        }
    }

    @Override
    public Room sortRoomByCost(double cost) throws DaoException {
        return null;
    }

    @Override
    public List<Room> findFreeRoomsByBooking(Booking booking) throws DaoException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByPrStatement(proxyConnection, FIND_FREE_ROOMS_BY_BOOKING,
                    (preparedStatement, params) -> {
                        preparedStatement.setString(1, booking.getStartDate());
                        preparedStatement.setString(2, booking.getEndDate());
                        preparedStatement.setInt(3, booking.getNumberOfRooms());
                        preparedStatement.setInt(4, booking.getNumberOfBeds());
                        preparedStatement.setInt(5, booking.getMaxPersons());
                        preparedStatement.setBoolean(5, booking.isHasWifi());
                        preparedStatement.setBoolean(5, booking.isHasTV());
                        preparedStatement.setBoolean(5, booking.isHasBathroom());
                    }, booking);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem in UserDao, while trying to fina all users", e);
        }
    }

    @Override
    protected Room makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String roomName = rs.getString(2);
        int numberOfRooms = rs.getInt(3);
        int maxPersons = rs.getInt(4);
        BigDecimal cost = rs.getBigDecimal(5);
        boolean hasWifi = rs.getBoolean(6);
        boolean hasTV = rs.getBoolean(7);
        boolean hasBathroom = rs.getBoolean(8);
        int numberOfBeds = rs.getInt(9);
        String description = rs.getString(10);
        String photoPath = rs.getString(11);
        RoomGrade gradeName = RoomGrade.valueOf(rs.getString(12).toUpperCase());
        return new Room(id, roomName, numberOfRooms, maxPersons, cost, hasWifi, hasTV, hasBathroom, numberOfBeds, description, photoPath, gradeName);
    }

    @Override
    public void createRoom(Room room) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_ROOM)) {
                preparedSt.setString(1, room.getName());
                preparedSt.setInt(2, room.getNumberOfRooms());
                preparedSt.setInt(3, room.getMaxPersons());
                preparedSt.setBigDecimal(4, room.getCost());
                preparedSt.setBoolean(5, room.isHasWifi());
                preparedSt.setBoolean(6, room.isHasTV());
                preparedSt.setBoolean(7, room.isHasBathroom());
                preparedSt.setInt(8, room.getNumberOfBeds());
                preparedSt.setString(9, room.getDescription());
                preparedSt.setString(10, room.getPhotoPath());
                preparedSt.setInt(11, room.getRoomGrade().ordinal());
                if (preparedSt.executeUpdate() != 1) {
                    throw new DaoException("Room was not created");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with create room", e);
        }
    }

    @Override
    public void deleteRoomById(int roomId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(DELETE_ROOM)) {
                preparedSt.setInt(1, roomId);
                if (preparedSt.executeUpdate() != 1) {
                    throw new DaoException("Room was not created");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with create room", e);
        }
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
