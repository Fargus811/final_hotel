package by.sergeev.hotel.exception;

/**
 * The type Connection pool exception.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class ConnectionPoolException extends Exception{

    /**
     * Instantiates a new Connection pool exception.
     */
    public ConnectionPoolException() {
        super();
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message the message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param cause the cause
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
