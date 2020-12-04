package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractJDBCDao;
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

/**
 * The type Room dao.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class RoomDaoImpl extends AbstractJDBCDao<Room> implements RoomDao {

    private static final Logger LOGGER = LogManager.getLogger(RoomDaoImpl.class);

    private static final String INSERTED_COLUMNS = "rooms.name, rooms.number_of_rooms, rooms.max_persons, " +
            "rooms.cost, rooms.has_Wifi, rooms.has_TV, rooms.has_bathroom, rooms.number_of_beds,rooms.room_description, rooms.photo_path, rooms.grade_id";
    private static final String SELECTED_COLUMNS = "rooms.id, " + INSERTED_COLUMNS;
    private static final String CREATE_ROOM = "INSERT INTO rooms (" + INSERTED_COLUMNS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ROOM_INFO = "UPDATE rooms SET rooms.name = ?, rooms.number_of_rooms= ?, rooms.max_persons = ?, " +
            "rooms.cost = ?, rooms.has_Wifi = ?, rooms.has_TV=?, rooms.has_bathroom=?, rooms.number_of_beds=?, rooms.room_description=?," +
            " rooms.photo_path=?, rooms.grade_id=?  WHERE id = ?";
    private static final String FIND_ALL_ROOMS = "SELECT " + SELECTED_COLUMNS + " FROM rooms";
    private static final String GET_ROOMS_BY_PAGE = "SELECT " + SELECTED_COLUMNS + " FROM rooms INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id LIMIT ?, 5";
    private static final String FIND_ROOM_BY_ID = "SELECT " + SELECTED_COLUMNS + " FROM rooms INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id WHERE rooms.id = ?";
    private static final String GET_COUNT_OF_ROWS = "SELECT COUNT(*) FROM rooms";
    private static final String DELETE_ROOM = "DELETE FROM rooms WHERE id = ? ";
    private static final String FIND_FREE_ROOMS_BY_BOOKING = "SELECT " + SELECTED_COLUMNS + " FROM rooms " +
            "WHERE rooms.number_of_rooms >= ? AND rooms.number_of_beds >= ? AND rooms.max_persons >= ?" +
            " AND rooms.grade_id = ? AND rooms.has_Wifi >= ? AND rooms.has_TV >= ? AND rooms.has_bathroom >= ?" +
            " AND id NOT IN (" +
            "SELECT bookings.room_id FROM bookings WHERE" +
            "( ? <= bookings.end_date AND bookings.start_date >= ? ))";

    @Override
    public List<Room> findAll() throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByQuery(connection, FIND_ALL_ROOMS);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.fatal("Problem in RoomDao, while trying to find all rooms");
            throw new DaoException("Problem in RoomDao, while trying to find all rooms", e);
        }
    }

    @Override
    public List<Room> getRoomsByPage(int offset) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByPrStatement(connection, GET_ROOMS_BY_PAGE,
                    ((preparedStatement, params) -> preparedStatement.setInt(1, offset)), offset);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.fatal("Problem with get rooms by page");
            throw new DaoException("Problem in RoomDao, while trying to get rooms by page in room dao", e);
        }
    }

    @Override
    public int getCountOfRows() throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(GET_COUNT_OF_ROWS)) {
                ResultSet resultSet = preparedSt.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.fatal("Problem with get count of room");
            throw new DaoException("Problem with get count of room", e);
        }
        return 0;
    }

    @Override
    public Optional<Room> findRoomById(long roomId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return Optional.ofNullable(tryFindEntityByPrStatement(connection, FIND_ROOM_BY_ID,
                    ((preparedStatement, params) -> preparedStatement.setLong(1, roomId)), roomId));
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.fatal("Problem with find room by id");
            throw new DaoException("Problem with bookingDao to find room by id in room dao", e);
        }
    }

    @Override
    public List<Room> findFreeRoomsByBooking(Booking booking) throws DaoException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByPrStatement(proxyConnection, FIND_FREE_ROOMS_BY_BOOKING,
                    (preparedStatement, params) -> {
                        preparedStatement.setInt(1, booking.getNumberOfRooms());
                        preparedStatement.setInt(2, booking.getNumberOfBeds());
                        preparedStatement.setInt(3, booking.getMaxPersons());
                        preparedStatement.setInt(4, booking.getRoomGrade().ordinal());
                        preparedStatement.setBoolean(5, booking.isHasWifi());
                        preparedStatement.setBoolean(6, booking.isHasTV());
                        preparedStatement.setBoolean(7, booking.isHasBathroom());
                        preparedStatement.setString(8, booking.getStartDate());
                        preparedStatement.setString(9, booking.getEndDate());
                    }, booking);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.fatal("Problem with find free rooms");
            throw new DaoException("Problem in UserDao, while trying to find free rooms", e);
        }
    }

    @Override
    protected Room makeEntity(ResultSet rs) throws SQLException {
        long id = rs.getInt(1);
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
        RoomGrade gradeName = RoomGrade.values()[(rs.getInt(12))];
        return new Room(id, roomName, numberOfRooms, maxPersons, cost, hasWifi, hasTV, hasBathroom, numberOfBeds, description, photoPath, gradeName);
    }

    @Override
    public void createRoom(Room room) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_ROOM)) {
                prepareStatement(room, preparedSt);
                if (preparedSt.executeUpdate() != 1) {
                    LOGGER.fatal("Room was not created");
                    throw new DaoException("Room was not created");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.fatal("Room was not created");
            throw new DaoException("Problem with create room in room dao", e);
        }
    }

    private void prepareStatement(Room room, PreparedStatement preparedSt) throws SQLException {
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
    }

    @Override
    public void deleteRoomById(long roomId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(DELETE_ROOM)) {
                preparedSt.setLong(1, roomId);
                if (preparedSt.executeUpdate() != 1) {
                    LOGGER.fatal("Room was not deleted");
                    throw new DaoException("Room was not deleted");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.fatal("Room was not deleted");
            throw new DaoException("Problem in delete room method in room dao", e);
        }
    }

    @Override
    public void updateRoomInfo(Room room) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(UPDATE_ROOM_INFO)) {
                prepareStatement(room, preparedSt);
                preparedSt.setLong(12, room.getId());
                if (preparedSt.executeUpdate() != 1) {
                    LOGGER.fatal("Room was not updated");
                    throw new DaoException("Room was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.fatal("Room was not updated");
            throw new DaoException("Problem with update room", e);
        }
    }

    @Override
    public void updateRoomPhoto(long roomId, String newPath) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(UPDATE_ROOM_INFO)) {
                preparedSt.setString(1, newPath);
                preparedSt.setLong(2, roomId);
                if (preparedSt.executeUpdate() != 1) {
                    LOGGER.fatal("Room was not updated");
                    throw new DaoException("Room was not update");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.fatal("Room was not updated");
            throw new DaoException("Problem with create room", e);
        }
    }
}

