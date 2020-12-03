package by.sergeev.hotel.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DateValidatorTest {

    @DataProvider(name = "correctTestDate")
    public Object[][] returnCorrectDateToCheck() {
        return new Object[][]{
                {"2021-02-12", "2021-03-15"},
                {"2022-01-25", "2022-01-27"},
                {"2022-04-14", "2022-04-30"},
                {"2020-12-20", "2020-12-25"},
                {"2020-12-10", "2021-11-03"},
        };
    }

    @DataProvider(name = "incorrectTestDate")
    public Object[][] returnIncorrectDateToCheck() {
        return new Object[][]{
                {"2021-02-12", "2020-03-15"},
                {"2022-11-25", "2022-01-27"},
                {"2000-04-14", "2022-01-22"},
                {"2020-11-17", "2022-12-25"},
                {"2020-03-11", "2021-11-03"},
        };
    }

    @Test(dataProvider = "correctTestDate")
    public void testIsValidStartAndEndDateReturnTrue(String startDate, String endDate) {
        boolean condition = DateValidator.isValidStartAndEndDate(startDate, endDate);
        assertTrue(condition);
    }

    @Test(dataProvider = "incorrectTestDate")
    public void testIsValidStartAndEndDateReturnFalse(String startDate, String endDate) {
        boolean condition = DateValidator.isValidStartAndEndDate(startDate, endDate);
        assertFalse(condition);
    }
}
