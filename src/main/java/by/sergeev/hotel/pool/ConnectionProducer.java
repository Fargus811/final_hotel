package by.sergeev.hotel.pool;

import by.sergeev.hotel.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionProducer {

    private String url;
    private Properties configProp;

    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";
    private static final String AUTO_RECONNECT = "db.autoReconnect";
    private static final String CHARACTER_ENCODING = "db.encoding";
    private static final String USE_UNICODE = "db.useUnicode";
    private static final String DATABASE_KEY_PROPERTY = "database";
    private static final String USER_KEY_PROPERTY = "user";
    private static final String PASSWORD_KEY_PROPERTY = "password";
    private static final String AUTO_RECONNECT_KEY_PROPERTY = "autoReconnect";
    private static final String CHARACTER_ENCODING_KEY_PROPERTY = "encoding";
    private static final String USE_UNICODE_KEY_PROPERTY = "useUnicode";

    ConnectionProducer() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_KEY_PROPERTY);
        configProp = new Properties();
        url = resourceBundle.getString(URL);
        configProp.put(USER_KEY_PROPERTY, resourceBundle.getString(USER));
        configProp.put(PASSWORD_KEY_PROPERTY, resourceBundle.getString(PASSWORD));
        configProp.put(AUTO_RECONNECT_KEY_PROPERTY, resourceBundle.getString(AUTO_RECONNECT));
        configProp.put(CHARACTER_ENCODING_KEY_PROPERTY, resourceBundle.getString(CHARACTER_ENCODING));
        configProp.put(USE_UNICODE_KEY_PROPERTY, resourceBundle.getString(USE_UNICODE));
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   public ProxyConnection produce() throws ConnectionPoolException {
        try {
            return tryProduce();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection was not produced", e);
        }

    }

    private ProxyConnection tryProduce() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, configProp);
        ProxyConnection proxyConnection = new ProxyConnection(connection);
        return proxyConnection;
    }

}
