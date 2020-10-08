package by.sergeev.hotel.dao.impl;

import by.sergeev.hotel.dao.AbstractDao;
import by.sergeev.hotel.dao.RoomDao;
import by.sergeev.hotel.entity.Room;
import by.sergeev.hotel.exception.DaoException;
import by.sergeev.hotel.pool.ProxyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    private static final String INSERTED_COLUMNS = "rooms.name, rooms.number_of_rooms, rooms.floor, rooms.max_persons, " +
            "rooms.cost, rooms.has_Wifi, rooms.has_TV, rooms.has_bathroom, rooms.number_of_beds,rooms.room_description, rooms.photo_path, grades.grade_name";
    private static final String SELECTED_COLUMNS = "rooms.id, " + INSERTED_COLUMNS;
    private static final String FIND_ALL_ROOMS_SQL = "SELECT " + SELECTED_COLUMNS +" FROM rooms INNER JOIN grades ON " +
            "grades.id  = rooms.grade_id";

   // SELECT hotel.rooms.id, hotel.rooms.name, hotel.rooms.number_of_rooms, hotel.rooms.floor, hotel.rooms.max_persons, hotel.rooms.cost, hotel.rooms.has_Wifi, hotel.rooms.has_TV, hotel.rooms.has_bathroom, hotel.rooms.number_of_beds, hotel.rooms.room_description, hotel.rooms.photo_path,
    // hotel.grades.grade_name FROM hotel.rooms INNER JOIN hotel.grades ON hotel.grades.id  = hotel.rooms.grade_id


    @Override
    public List<Room> findAll(ProxyConnection proxyConnection) throws DaoException {
        System.out.println("findAll in DAO");
        try{
            return tryFindEntityListByQuery(proxyConnection,FIND_ALL_ROOMS_SQL);
        } catch (SQLException e){
            throw new DaoException("Problem in RoomDao, while trying to fina all themes", e);
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
    public List<Room> findAll() throws DaoException {
        return null;
    }

    @Override
    public Room findEntityById(int id) throws DaoException {
        return null;
    }

    @Override
    public void create(Room room) throws DaoException {

    }

    @Override
    public void update(Room room) throws DaoException {

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
        String gradeName = rs.getString(13);
        return new Room(id, roomName, numberOfRooms, floor, maxPersons, cost, hasWifi,hasTV, hasBathroom, numberOfBeds, description, photoPath, gradeName);
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
