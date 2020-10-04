package by.sergeev.hotel.pool;

import by.sergeev.hotel.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProxyConnection implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();

    private Connection connection;

    ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    public void close() {
        try {
            ConnectionPool.getInstance().putConnection(this);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection was not put to connectionpool");
        }
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void realClose() throws SQLException {
        connection.close();
    }

    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }
}
