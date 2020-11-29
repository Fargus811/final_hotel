package by.sergeev.hotel.util.mail;

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
