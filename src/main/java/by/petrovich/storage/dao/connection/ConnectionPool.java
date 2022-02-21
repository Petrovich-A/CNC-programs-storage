package by.petrovich.storage.dao.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.util.PropertyLoader;

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
	private static final Logger logger = LogManager.getLogger();
	private static final ReentrantLock reentrantLock = new ReentrantLock(true);
	private static final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
	private static ConnectionPool instance;
	private static final int DEFAULT_POOL_SIZE = 20;
	private static final PropertyLoader READER = new PropertyLoader();
	private static final String PROP_USERNAME = "username";
	private static final String PROP_PASSWORD = "password";
	private static final String PROP_DRIVER_CLASS_NAME = "driverClassName";
	private static final String PROP_URL = "url";
	private BlockingDeque<ProxyConnection> freeConnections;
	private Queue<ProxyConnection> givenAwayConnections;

	static {
		try {
			Class.forName(READER.get(PROP_DRIVER_CLASS_NAME));
		} catch (ClassNotFoundException e) {
			logger.log(Level.ERROR, "can't get PROP_DRIVER_CLASS_NAME: {} ", PROP_DRIVER_CLASS_NAME, e);
		}
	}

	private ConnectionPool() {
		freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
		givenAwayConnections = new ArrayDeque<>();
		try {
			for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
				Connection connection = DriverManager.getConnection(READER.get(PROP_URL), READER.get(PROP_USERNAME),
						READER.get(PROP_PASSWORD));
				freeConnections.offer(new ProxyConnection(connection));
				logger.log(Level.INFO, "connection have been got");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "proxyConnection isn't created", e);
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
		ProxyConnection connection = null;
		try {
			connection = freeConnections.take();
			givenAwayConnections.offer(connection);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public boolean releaseConnection(ProxyConnection connection) {
		if (!(connection instanceof ProxyConnection)) {
			return false;
		}
		givenAwayConnections.remove(connection);
		freeConnections.offer(connection);
		return true;
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
