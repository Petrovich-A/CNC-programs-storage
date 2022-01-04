package by.petrovich.storage.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final static Logger logger = LogManager.getLogger();
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static ConnectionPool instance;
    private BlockingDeque<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    private static final int DEFAULT_POOL_SIZE = 12;
    private static ProxyConnection proxyConnection;

    private ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            reentrantLock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
            }
            reentrantLock.unlock();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(proxyConnection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        InitialContext initialContext;
        DataSource dataSource;
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/CncProgramPool");
            connection = dataSource.getConnection();
            logger.log(Level.DEBUG, "connection have been got", connection);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Naming problem", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Connection isn't available", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        givenAwayConnections.remove(connection);
        freeConnections.offer(proxyConnection);
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deregisterDrivers error", e);
            }
        });
    }

}
