package by.sergeev.hotel.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Mail sender.
 *
 * @version 1.0
 */
public class MailSender {

    private static final Logger logger = LogManager.getLogger();
    private static final String CONFIG_FILEPATH = "mail/mail.properties";
    private static final String USER_NAME = "mail.user.name";
    private static final String USER_PASSWORD = "mail.user.password";

    private static String username;
    private static String password;

    private MailSender() {
    }

    /**
     * Send mail.
     *
     * @param recipientAddress the recipient address
     * @param subject          the subject
     * @param text             the text
     */
    public static void sendMail(String recipientAddress, String subject, String text) {
        try {
            Properties properties = getProperties();
            username = properties.getProperty(USER_NAME);
            password = properties.getProperty(USER_PASSWORD);
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get(USER_NAME)));
            message.setSubject(subject);
            message.setText(text);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));
            Transport.send(message);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Error occurred when email sending to " + recipientAddress, e);
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(CONFIG_FILEPATH);
        try {
            properties.load(stream);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error occurred when email sending, problem with mail api properties data", e);
        }

        return properties;
    }
}
