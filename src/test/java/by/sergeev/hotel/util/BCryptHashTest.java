package by.sergeev.hotel.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BCryptHashTest {

    @Test
    public void testCheckPassword() {
        String passwordHashed = BCryptHash.hashPassword("40Ofariv");
        boolean result = BCryptHash.checkPassword("40Ofariv", passwordHashed);
        assertTrue(result);
    }
}
