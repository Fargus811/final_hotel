package by.sergeev.hotel.util;

import by.sergeev.hotel.exception.CommandException;

public interface ClientNotificationSender {
    /**
     * Register mail notification.
     *
     * @param clientEmail     the client email
     * @param clientFirstName the client first name
     * @param link            the link
     * @throws CommandException the service project exception
     */
    void registerMailNotification(String clientEmail, String clientFirstName, String link) throws CommandException;

    /**
     * Create order notification.
     *
     * @param clientEmail the client email
     * @throws CommandException the service project exception
     */
    void createOrderNotification(String clientEmail) throws CommandException;
}
