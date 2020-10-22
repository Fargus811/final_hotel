package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.ConnectionPoolException;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ConnectionPool;
import by.sergeev.hotel.pool.ProxyConnection;
import by.sergeev.hotel.util.EntityAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    private static final String INSERTED_COLUMNS = "rooms.name, rooms.number_of_rooms, rooms.floor, rooms.max_persons, " +
            "rooms.cost, rooms.has_Wifi, rooms.has_TV, rooms.has_bathroom, rooms.number_of_beds,rooms.room_description, rooms.photo_path, grades.grade_name";
    private static final String SELECTED_COLUMNS = "rooms.id, " + INSERTED_COLUMNS;
    private static final String FIND_ALL_ROOMS_SQL = "SELECT " + SELECTED_COLUMNS + " FROM rooms INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id";

    // SELECT hotel.rooms.id, hotel.rooms.name, hotel.rooms.number_of_rooms, hotel.rooms.floor, hotel.rooms.max_persons, hotel.rooms.cost, hotel.rooms.has_Wifi, hotel.rooms.has_TV, hotel.rooms.has_bathroom, hotel.rooms.number_of_beds, hotel.rooms.room_description, hotel.rooms.photo_path,
    // hotel.grades.grade_name FROM hotel.rooms INNER JOIN hotel.grades ON hotel.grades.id  = hotel.rooms.grade_id


    @Override
    public List<Room> findAll() throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection()) {
            return tryFindEntityListByQuery(connection, FIND_ALL_ROOMS_SQL);
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
    protected Room makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(EntityAttribute.FIRST_ATTRIBUTE);
        String roomName = rs.getString(EntityAttribute.SECOND_ATTRIBUTE);
        int numberOfRooms = rs.getInt(EntityAttribute.THIRD_ATTRIBUTE);
        int floor = rs.getInt(EntityAttribute.FOURTH_ATTRIBUTE);
        int maxPersons = rs.getInt(EntityAttribute.FIFTH_ATTRIBUTE);
        double cost = rs.getDouble(EntityAttribute.SIXTH_ATTRIBUTE);
        boolean hasWifi = rs.getBoolean(EntityAttribute.SEVENTH_ATTRIBUTE);
        boolean hasTV = rs.getBoolean(EntityAttribute.EIGHTH_ATTRIBUTE);
        boolean hasBathroom = rs.getBoolean(EntityAttribute.NINTH_ATTRIBUTE);
        int numberOfBeds = rs.getInt(EntityAttribute.TENTH_ATTRIBUTE);
        String description = rs.getString(EntityAttribute.ELEVENTH_ATTRIBUTE);
        String photoPath = rs.getString(EntityAttribute.TWELFTH_ATTRIBUTE);
        String gradeName = rs.getString(EntityAttribute.THIRTEENTH_ATTRIBUTE);
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
