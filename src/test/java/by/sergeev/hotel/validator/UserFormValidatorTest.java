package by.sergeev.hotel.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserFormValidatorTest {

    @Test
    public void testIsValidEmailReturnTrue() {
        boolean condition = UserFormValidator.isValidEmail("ggas@gmail.com");
        assertTrue(condition);
    }

    @Test
    public void testIsValidEmailReturnFalse() {
        boolean condition = UserFormValidator.isValidEmail("ggas@gmailcom");
        assertFalse(condition);
    }

    @Test
    public void testIsValidPasswordReturnTrue() {
        boolean condition = UserFormValidator.isValidPassword("40Ofariv");
        assertTrue(condition);
    }

    @Test
    public void testIsValidPasswordReturnFalse() {
        boolean condition = UserFormValidator.isValidPassword("40ofariv");
        assertFalse(condition);
    }

    @Test
    public void testIsValidFirstNameReturnTrue() {
        boolean condition = UserFormValidator.isValidFirstAndLastName("Daniil", "Sergeev");
        assertTrue(condition);
    }

    @Test
    public void testIsValidFirstNameReturnFalse() {
        boolean condition = UserFormValidator.isValidFirstAndLastName("daniil","Sergeev");
        assertFalse(condition);
    }
}
