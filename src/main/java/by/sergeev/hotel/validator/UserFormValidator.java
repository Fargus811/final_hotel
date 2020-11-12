package by.sergeev.hotel.validator;

public class UserFormValidator {

    public static boolean isValidEmail(String email) {
        return email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+");
    }

    public static boolean isValidPassword(String pass) {
        return pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,25}$");
    }

    public static boolean isValidFirstName(String firstName) {
        return firstName.matches("[A-ZА-Я][a-zа-я\\-]{1,32}");
    }

    public static boolean isValidLastName(String lastName) {
        return lastName.matches("[A-ZА-Я][a-zа-я\\-]{1,32}");
    }

    private UserFormValidator() {
    }
}
