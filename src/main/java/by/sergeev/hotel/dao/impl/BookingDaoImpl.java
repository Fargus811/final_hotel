package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.BookingDao;
import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.entity.enums.BookingStatus;
import by.sergeev.hotel.entity.enums.RoomGrade;
import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import by.sergeev.hotel.util.EntityAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookingDaoImpl extends AbstractDao<Booking> implements BookingDao {

    private static final Logger LOGGER = LogManager.getLogger(BookingDaoImpl.class);

    private static final String INSERTED_COLUMNS = "bookings.start_date, bookings.end_date, bookings.cost, " +
            "bookings.max_persons, bookings.number_of_beds, bookings.grade_id, bookings.has_Wifi, bookings.has_TV, " +
            "bookings.has_bathroom, bookings.user_id, bookings.number_of_rooms";
    private static final String SELECTED_COLUMNS = "bookings.id, booking.room_id, " + INSERTED_COLUMNS;
    private static final String CREATE_BOOKING_SQL = "INSERT INTO bookings (" + INSERTED_COLUMNS + ") VALUES " +
            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BOOKINGS_BY_USER_SQL = "SELECT id, start_date, end_date, cost, max_persons, number_of_beds," +
            " grade_id, has_Wifi, has_TV , has_bathroom, user_id, room_id, number_of_rooms, booking_status_id FROM bookings WHERE user_id = ?";
    private static final String UPDATE_BOOKING_STATUS_BY_ID_SQL = "UPDATE bookings SET status_id = ? WHERE id = ?";

    @Override
    public List<Booking> findBookingsByUserId(int userId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByPrStatement(connection, FIND_BOOKINGS_BY_USER_SQL,
                    ((preparedStatement, params) -> preparedStatement.setInt(1, userId)), userId);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Problem with bookingDao to find Bookings by userID", e);
        }
    }

    ///UPDATE bookings SET status_id = statusId WHERE id = bookingId
    @Override
    public void changeBookingStatusById(int bookingId, int statusId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(UPDATE_BOOKING_STATUS_BY_ID_SQL)) {
                preparedSt.setInt(1, bookingId);
                preparedSt.setInt(2, statusId);
                if (preparedSt.getUpdateCount() != 1) {
                    throw new DaoException("Status was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with change book status", e);
        }
    }

    @Override
    public void createBooking(Booking freshBooking) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            try (PreparedStatement preparedSt = connection.prepareStatement(CREATE_BOOKING_SQL)) {
                preparedSt.setString(1, freshBooking.getStartDate());
                preparedSt.setString(2, freshBooking.getEndDate());
                preparedSt.setDouble(3, freshBooking.getCost());
                preparedSt.setInt(4, freshBooking.getMaxPersons());
                preparedSt.setInt(5, freshBooking.getNumberOfBeds());
                preparedSt.setInt(6, freshBooking.getRoomGrade().getId());
                preparedSt.setBoolean(7, freshBooking.isHasWifi());
                preparedSt.setBoolean(8, freshBooking.isHasTV());
                preparedSt.setBoolean(9, freshBooking.isHasBathroom());
                preparedSt.setInt(10, freshBooking.getUserId());
                preparedSt.setInt(11, freshBooking.getNumberOfRooms());
                if (preparedSt.executeUpdate() != 1) {
                    throw new DaoException("Status was not updated");
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Problem with change book status", e);
        }

    }

    @Override
    protected Booking makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String startDate = rs.getString(2);
        String endDate = rs.getString(3);
        double cost = rs.getDouble(4);
        int maxPersons = rs.getInt(5);
        int numberOfBeds = rs.getInt(6);
        int gradeId = rs.getInt(7);
        boolean hasWifi = rs.getBoolean(8);
        boolean hasTV = rs.getBoolean(9);
        boolean hasBathroom = rs.getBoolean(10);
        int userId = rs.getInt(11);
        int roomId = Optional.ofNullable(rs.getInt(12)).orElse(-1);
        int numberOfRooms = rs.getInt(13);
        int statusId = rs.getInt(14);
        Room room = null;
        if (roomId != -1) {
            RoomDao roomDao = DaoFactory.daoFactory.getRoomDao();
            try {
                room = roomDao.findRoomById(roomId);
            } catch (DaoException e) {
                LOGGER.fatal("Can't find room by this id in bookingDao");
            }
        }
        BookingStatus status = BookingStatus.values()[statusId];
        RoomGrade roomGrade = RoomGrade.getRoomGrade(gradeId);
        return new Booking(id, startDate, endDate, cost, maxPersons, numberOfBeds, roomGrade, hasWifi, hasTV, hasBathroom,
                userId, room, status, numberOfRooms);
    }
}

