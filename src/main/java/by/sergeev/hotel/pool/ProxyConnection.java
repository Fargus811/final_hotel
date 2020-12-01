package by.sergeev.hotel.pool;

import by.sergeev.hotel.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


/**
 * The type Proxy connection.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class ProxyConnection implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(ProxyConnection.class);

    private Connection connection;

    /**
     * Instantiates a new Proxy connection.
     *
     * @param connection the connection
     */
    ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    public void close() {
        try {
            ConnectionPool.getInstance().putConnection(this);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection was not put to connection pool");
        }
    }

    /**
     * Create statement statement.
     *
     * @return the statement
     * @throws SQLException the sql exception
     */
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param sql the sql
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * Prepare call callable statement.
     *
     * @param sql the sql
     * @return the callable statement
     * @throws SQLException the sql exception
     */
    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * Native sql string.
     *
     * @param sql the sql
     * @return the string
     * @throws SQLException the sql exception
     */
    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * Sets auto commit.
     *
     * @param autoCommit the auto commit
     * @throws SQLException the sql exception
     */
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     * Gets auto commit.
     *
     * @return the auto commit
     * @throws SQLException the sql exception
     */
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     * Commit.
     *
     * @throws SQLException the sql exception
     */
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Rollback.
     *
     * @throws SQLException the sql exception
     */
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Real close.
     *
     * @throws SQLException the sql exception
     */
    public void realClose() throws SQLException {
        connection.close();
    }

    /**
     * Is closed boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     * Is valid boolean.
     *
     * @param timeout the timeout
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }
}
