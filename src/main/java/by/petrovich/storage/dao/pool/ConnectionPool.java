package by.petrovich.storage.dao.pool;

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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final static Logger logger = LogManager.getLogger();
    private static final ReentrantLock reentrantLock = new ReentrantLock(true);
    private static final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private BlockingDeque<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    private static final int DEFAULT_POOL_SIZE = 20;
    private static final String DRIVER_SQL = "java:comp/env/jdbc/CncProgramPool";

    private ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(DRIVER_SQL);
            ProxyConnection proxyConnection = new ProxyConnection(dataSource.getConnection());
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                freeConnections.offer(proxyConnection);
                logger.log(Level.DEBUG, "connection have been got", proxyConnection);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "proxyConnection isn't created", e);
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (!atomicBoolean.get()) {
            reentrantLock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    atomicBoolean.set(true);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            givenAwayConnections.offer(proxyConnection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return proxyConnection;
    }

    public void releaseConnection(ProxyConnection proxyConnection) {
//        if (proxyConnection){
//
//        }
        givenAwayConnections.remove(proxyConnection);
        freeConnections.offer(proxyConnection);
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregistrateDrivers();
    }

    private void deregistrateDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deregisterDrivers error", e);
            }
        });
    }

}