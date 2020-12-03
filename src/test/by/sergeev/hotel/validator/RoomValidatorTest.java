package by.sergeev.hotel.validator;

import by.sergeev.hotel.entity.Room;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertFalse;

public class RoomValidatorTest {

    Room roomValid;
    Room roomInvalid;
    @BeforeMethod
    public void setUp() {
        roomValid = new Room();
        roomValid.setName("Семейный");
        roomValid.setCost(BigDecimal.valueOf(599));
        roomValid.setDescription("Отдых для семьи");
        roomInvalid = new Room();
        roomInvalid.setName("Семейный11");
        roomInvalid.setCost(BigDecimal.valueOf(99));
        roomInvalid.setDescription("Отдых для family");
    }

    @Test
    public void testIsRoomValidReturnFalse() {
        boolean condition = RoomValidator.isRoomValid(roomInvalid);
        assertFalse(condition);
    }
    @Test
    public void testIsRoomValidReturnTrue() {
        Room room = new Room();
        boolean condition = RoomValidator.isRoomValid(roomInvalid);
        assertFalse(condition);
    }
}
