package by.sergeev.hotel.pool;

import by.sergeev.hotel.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionProducer {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionProducer.class);
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "40ofariv";
    private static final String AUTO_RECONNECT = "true";
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String USE_UNICODE = "true";
    private static final String USE_SSL = "false";

    private Properties configProp;

    {
        configProp = new Properties();
        configProp.put("user", USER);
        configProp.put("password", PASSWORD);
        configProp.put("autoReconnect", AUTO_RECONNECT);
        configProp.put("characterEncoding", CHARACTER_ENCODING);
        configProp.put("useUnicode", USE_UNICODE);
        configProp.put("useSSL", USE_SSL);
    }


    ConnectionProducer() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.fatal("Problem with DriverManager registration", e);
            throw new RuntimeException(e);
        }
    }

    ProxyConnection produce() throws ConnectionPoolException {
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
//TODO new Connection Producer
//    private String url;
//    private Properties configProp;
//
//    private static final String DATABASE_NAME_PROPERTIES = "database";
//    private static final String URL = "db.url";
//    private static final String USER = "db.user";
//    private static final String PASSWORD = "db.password";
//    private static final String AUTO_RECONNECT = "db.autoReconnect";
//    private static final String CHARACTER_ENCODING = "db.characterEncoding";
//    private static final String USE_UNICODE = "db.useUnicode";
//
//    ConnectionProducer() {
//        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_NAME_PROPERTIES);
//        configProp = new Properties();
//        url = resourceBundle.getString(URL);
//        configProp.put("user", resourceBundle.getString(USER));
//        configProp.put("password", resourceBundle.getString(PASSWORD));
//        configProp.put("autoReconnect", resourceBundle.getString(AUTO_RECONNECT));
//        configProp.put("characterEncoding", resourceBundle.getString(CHARACTER_ENCODING));
//        configProp.put("useUnicode", resourceBundle.getString(USE_UNICODE));
//        try {
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        } catch (SQLException e) {
//            LOGGER.fatal("Problem with DriverManager registration", e);
//            throw new RuntimeException(e);
//        }
//    }
//
//    public ProxyConnection produce() throws ConnectionPoolException {
//        try {
//            Connection connection = DriverManager.getConnection(URL, configProp);
//            return new ProxyConnection(connection);
//        } catch (SQLException e) {
//            throw new ConnectionPoolException("Connection was not produced", e);
//        }
//    }

