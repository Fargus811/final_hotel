package by.sergeev.hotel.util.mail;

/**
 * The interface Client notification sender.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public interface ClientNotificationSender {
    /**
     * Register mail notification.
     *
     * @param clientEmail     the client email
     * @param clientFirstName the client first name
     * @param link            the link
     */
    void registerMailNotification(String clientEmail, String clientFirstName, String link);

    /**
     * Create order notification.
     *
     * @param clientEmail the client email
     */
    void createOrderNotification(String clientEmail);
}
