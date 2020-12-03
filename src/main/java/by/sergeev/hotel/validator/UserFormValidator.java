package by.sergeev.hotel.validator;


/**
 * The type User form validator.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class UserFormValidator {

    private static final String REGEX_FOR_EMAIL = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+";
    private static final String REGEX_FOR_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,25}$";
    private static final String REGEX_FOR_NAME = "[A-ZА-Я][a-zа-я\\-]{1,32}";

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        return email.matches(REGEX_FOR_EMAIL);
    }

    /**
     * Is valid password boolean.
     *
     * @param pass the pass
     * @return the boolean
     */
    public static boolean isValidPassword(String pass) {
        return pass.matches(REGEX_FOR_PASSWORD);
    }

    /**
     * Is valid first name boolean.
     *
     * @param firstName the first name
     * @return the boolean
     */
    public static boolean isValidFirstAndLastName(String firstName, String lastName) {
        return firstName.matches(REGEX_FOR_NAME) && lastName.matches(REGEX_FOR_NAME);
    }


    private UserFormValidator() {
    }
}
