package by.sergeev.hotel.validator;

import java.time.LocalDate;

public class DateValidator {

    public static boolean isValidStartAndEndDate(String startDate, String endDate){
        LocalDate currentLocalDate = LocalDate.now();
        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = LocalDate.parse(endDate);
        return currentLocalDate.compareTo(startLocalDate) >= 1 && startLocalDate.compareTo(endLocalDate) >= 1;
    }

    private DateValidator() {
    }
}
