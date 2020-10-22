package by.sergeev.hotel.dao;

import by.sergeev.hotel.entity.Booking;
import by.sergeev.hotel.entity.User;

import java.util.HashMap;
import java.util.Map;

public class QueryGenerator {

    private static final String USER_SELECTED_COLUMNS = "id, email, password, first_name, last_name, balance," +
            "role_id";
    private static final String USER_TABLE = "users";
    private static final String BOOKING_SELECTED_COLUMNS = "bookings.id, bookings.start_date, bookings.end_date, bookings.cost, " +
            "bookings.max_persons, bookings.number_of_rooms, bookings.grade_id, bookings.has_Wifi, bookings.has_TV, " +
            "bookings.has_bathroom, bookings.user_id,bookings.room_id, bookings.photo_path, grades.grade_name";
    private static final String BOOKING_TABLE = "bookings";

    private static final Map<Class, String> selectColumnsByClass;
    private static final Map<Class, String> tableNameByClass;

    static {
        tableNameByClass = new HashMap<>();
        tableNameByClass.put(User.class,USER_TABLE);
        tableNameByClass.put(Booking.class,BOOKING_TABLE);
    }

    static {
        selectColumnsByClass = new HashMap<>();
        selectColumnsByClass.put(User.class, USER_SELECTED_COLUMNS);
        selectColumnsByClass.put(Booking.class,BOOKING_SELECTED_COLUMNS);
    }

    public static String createFindAllQuery(Class entityClass){
        return "SELECT " + selectColumnsByClass.get(entityClass) + " FROM " + tableNameByClass.get(entityClass);
    }

}
