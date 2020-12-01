package by.sergeev.hotel.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * The type B crypt hash.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class BCryptHash {

    private static final int WORKLOAD = 12;

    /**
     * Hash password string.
     *
     * @param passwordPlaintext the password plaintext
     * @return the string
     */
    public static String hashPassword(String passwordPlaintext) {
        String salt = BCrypt.gensalt(WORKLOAD);
        String hashedPassword = BCrypt.hashpw(passwordPlaintext, salt);

        return (hashedPassword);
    }

    /**
     * Check password boolean.
     *
     * @param passwordPlaintext the password plaintext
     * @param storedHash        the stored hash
     * @return the boolean
     */
    public static boolean checkPassword(String passwordPlaintext, String storedHash) {
        boolean passwordVerified;
        if (null == storedHash || !storedHash.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash provided for comparison");
        }
        passwordVerified = BCrypt.checkpw(passwordPlaintext, storedHash);
        return (passwordVerified);
    }

}
