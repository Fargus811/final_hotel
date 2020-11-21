package by.sergeev.hotel.validator;

import by.sergeev.hotel.entity.Room;

import java.math.BigDecimal;

public class RoomValidator {

    private static final String ROOM_NAME_REGEX = "^[А-Яа-яЁё\\s]+$";
    private static final String ROOM_DESC_REGEX = "^[А-Яа-яЁё\\s]+$";
    private static final BigDecimal MIN_COST = BigDecimal.valueOf(149.99);
    private static final BigDecimal MAX_COST = BigDecimal.valueOf(20000.01);


    public static boolean isRoomValid(Room room) {
        if (!(room.getCost().compareTo(MIN_COST) > 0 & room.getCost().compareTo(MAX_COST) < 0)) {
            return false;
        }
        return room.getName().matches(ROOM_NAME_REGEX) && room.getDescription().matches(ROOM_DESC_REGEX);
    }

    private RoomValidator() {
    }
}
