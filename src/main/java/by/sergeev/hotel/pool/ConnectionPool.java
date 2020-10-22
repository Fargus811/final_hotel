package by.sergeev.hotel.pool;

import by.sergeev.hotel.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);

    private static final int POOL_SIZE = 8;
    private static final int TIMEOUT_VALID = 3;

    private BlockingQueue<ProxyConnection> availableConnections;

    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static Lock initializationLock = new ReentrantLock();

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            if (isInitialized.compareAndSet(false, true)) {
                initializationLock.lock();
                try {
                    if (instance == null) {
                        instance = new ConnectionPool();
                    }
                } finally {
                    initializationLock.unlock();
                }
            }
        }
        return instance;
    }

    private ConnectionPool() {
        availableConnections = new ArrayBlockingQueue<ProxyConnection>(POOL_SIZE);
        initConnections();
    }

    private void initConnections() {
        while (availableConnections.size() != POOL_SIZE) {
            int rest = POOL_SIZE - availableConnections.size();
            for (int i = 0; i < rest; i++) {
                try {
                    ProxyConnection connection = tryProduceConnection();
                    availableConnections.put(connection);
                    LOG.info("Connection was initialized and added to connection pool");
                } catch (SQLException | ConnectionPoolException | InterruptedException e) {
                    LOG.warn("Connection was not added to connection pool");
                }
            }
            checkSize();
        }
    }

    private ProxyConnection tryProduceConnection() throws ConnectionPoolException, SQLException {
        ConnectionProducer connectionProducer = new ConnectionProducer();
        ProxyConnection connection = connectionProducer.produce();
        connection.setAutoCommit(true);
        return connection;
    }

    private void checkSize() {
        if (availableConnections.isEmpty()) {
            LOG.fatal("Pool was not initialized");
            throw new RuntimeException();
        }
    }

    public ProxyConnection takeConnection() throws ConnectionPoolException {
        try {
            ProxyConnection connection = availableConnections.take();
            LOG.info("Connection was taken from connection pool");
            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Exception in ConnectionPool while trying to take connection", e);
        }
    }

    public void putConnection(ProxyConnection connection) throws ConnectionPoolException {
        try {
            if (!connection.isValid(TIMEOUT_VALID)) {
                connection = tryProduceConnection();
            }
            availableConnections.put(connection);
            LOG.info("Connection was put to connection pool");
        } catch (ConnectionPoolException | InterruptedException | SQLException e) {
            throw new ConnectionPoolException("Exception in ConnectionPool while trying to put connection", e);
        }
    }

    public void closeAll() {
        if (isInitialized.compareAndSet(true, false)) {
            for (int i = 0; i < POOL_SIZE; i++) {
                closeConnection(i);
            }
        }
    }

    private void closeConnection(int i) {
        try {
            ProxyConnection connection = availableConnections.take();
            connection.realClose();
            LOG.info(String.format("closed successfully (#%d)", i));
        } catch (SQLException | InterruptedException e) {
            LOG.warn(String.format("problem with connection closing (#%d)", i));
        }
    }
}
