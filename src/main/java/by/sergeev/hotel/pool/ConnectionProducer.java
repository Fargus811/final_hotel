package by.sergeev.hotel.pool;

import by.sergeev.hotel.exception.ConnectionPoolException;
import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * The type Connection producer.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
class ConnectionProducer {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionProducer.class);
    private String url;
    private Properties configProp;

    private static final String DATABASE_NAME_PROPERTIES = "database";
    private static final String URL_PROP_NAME = "db.url";
    private static final String USER_PROP_NAME = "db.user";
    private static final String PASSWORD_PROP_NAME = "db.password";
    private static final String AUTO_RECONNECT_PROP_NAME = "db.autoReconnect";
    private static final String CHARACTER_ENCODING_PROP_NAME = "db.characterEncoding";
    private static final String USE_UNICODE_PROP_NAME = "db.useUnicode";

    /**
     * Instantiates a new Connection producer.
     */
    ConnectionProducer() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_NAME_PROPERTIES);
        configProp = new Properties();
        url = resourceBundle.getString(URL_PROP_NAME);
        configProp.put("user", resourceBundle.getString(USER_PROP_NAME));
        configProp.put("password", resourceBundle.getString(PASSWORD_PROP_NAME));
        configProp.put("autoReconnect", resourceBundle.getString(AUTO_RECONNECT_PROP_NAME));
        configProp.put("characterEncoding", resourceBundle.getString(CHARACTER_ENCODING_PROP_NAME));
        configProp.put("useUnicode", resourceBundle.getString(USE_UNICODE_PROP_NAME));
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Produce proxy connection.
     *
     * @return the proxy connection
     * @throws ConnectionPoolException the connection pool exception
     */
    public ProxyConnection produce() throws ConnectionPoolException {
        try {
            Connection connection = DriverManager.getConnection(url, configProp);
            return new ProxyConnection(connection);
        } catch (SQLException e) {
            LOGGER.fatal("Problem with produce connection");
            throw new ConnectionPoolException("Connection was not produced", e);
        }
    }
}

