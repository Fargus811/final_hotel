package by.sergeev.hotel.validator;


/**
 * The type User form validator.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class UserFormValidator {

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        return email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+");
    }

    /**
     * Is valid password boolean.
     *
     * @param pass the pass
     * @return the boolean
     */
    public static boolean isValidPassword(String pass) {
        return pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,25}$");
    }

    /**
     * Is valid first name boolean.
     *
     * @param firstName the first name
     * @return the boolean
     */
    public static boolean isValidFirstName(String firstName) {
        return firstName.matches("[A-ZА-Я][a-zа-я\\-]{1,32}");
    }

    /**
     * Is valid last name boolean.
     *
     * @param lastName the last name
     * @return the boolean
     */
    public static boolean isValidLastName(String lastName) {
        return lastName.matches("[A-ZА-Я][a-zа-я\\-]{1,32}");
    }

    private UserFormValidator() {
    }
}
