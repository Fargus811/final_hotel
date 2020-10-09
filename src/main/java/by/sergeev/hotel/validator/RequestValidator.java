package by.sergeev.hotel.validator;

public class RequestValidator {

    public static boolean isValidEmail(String email) {
        return email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+");
    }

    public static boolean isValidPassword(String pass) {
        return pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,25}$");
    }
}
