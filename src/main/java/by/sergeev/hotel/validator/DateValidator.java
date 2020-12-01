package by.sergeev.hotel.validator;

import java.time.LocalDate;

/**
 * The type Date validator.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class DateValidator {

    /**
     * Is valid start and end date boolean.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the boolean
     */
    public static boolean isValidStartAndEndDate(String startDate, String endDate){
        LocalDate currentLocalDate = LocalDate.now();
        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = LocalDate.parse(endDate);
        return currentLocalDate.compareTo(startLocalDate) <= 0 && startLocalDate.compareTo(endLocalDate) < 1;
    }

    private DateValidator() {
    }
}
