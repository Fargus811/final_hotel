package by.sergeev.hotel.util.mail.impl;

import by.sergeev.hotel.exception.CommandException;
import by.sergeev.hotel.util.mail.ClientNotificationSender;
import by.sergeev.hotel.util.mail.MailSender;

import java.util.ResourceBundle;
import java.util.StringJoiner;

public class ClientNotificationSenderImpl implements ClientNotificationSender {

    private static final String MESSAGE_FILENAME = "../mail/notification_message";
    private static final String REGISTRATION_MAIL_SUBJECT = "mail.registration.subject";
    private static final String REGISTRATION_TEXT_APPEAL = "mail.registration.appeal";
    private static final String REGISTRATION_TEXT_GRATITUDE = "mail.registration.gratitude";
    private static final String REGISTRATION_TEXT_FINISH = "mail.registration.finish_reg";
    private static final String REGISTRATION_LINK_SUFFIX = "?command=activate_account&email=";
    private static final String CREATE_ORDER_MAIL_SUBJECT = "mail.create_order.subject";
    private static final String CREATE_ORDER_MAIL_TEXT = "mail.create_order.text";
    private static final String TEXT_SEPARATOR = "\n";


    public void registerMailNotification(String clientEmail, String clientFirstName, String link) {
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_FILENAME);
        String mailSubject = bundle.getString(REGISTRATION_MAIL_SUBJECT);
        StringJoiner mailText = new StringJoiner(TEXT_SEPARATOR);
        mailText.add(bundle.getString(REGISTRATION_TEXT_APPEAL) + clientFirstName);
        mailText.add(bundle.getString(REGISTRATION_TEXT_GRATITUDE));
        mailText.add(bundle.getString(REGISTRATION_TEXT_FINISH));
        mailText.add(link + REGISTRATION_LINK_SUFFIX + clientEmail);
        MailSender.sendMail(clientEmail, mailSubject, mailText.toString());
    }

    public void createOrderNotification(String clientEmail) {
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_FILENAME);
        String mailSubject = bundle.getString(CREATE_ORDER_MAIL_SUBJECT);
        String mailText = bundle.getString(CREATE_ORDER_MAIL_TEXT);
        MailSender.sendMail(clientEmail, mailSubject, mailText);
    }
}
