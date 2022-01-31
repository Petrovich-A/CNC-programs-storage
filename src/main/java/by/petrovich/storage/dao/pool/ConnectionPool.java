package by.petrovich.storage.dao.pool;

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
	private BlockingDeque<ProxyConnection> freeConnections;
	private Queue<ProxyConnection> givenAwayConnections;
	private static final int DEFAULT_POOL_SIZE = 20;
	private static final PropertyLoader READER = new PropertyLoader();
	private static final String PROP_USERNAME = "username";
	private static final String PROP_PASSWORD = "password";
	private static final String PROP_DRIVER_CLASS_NAME = "driverClassName";
	private static final String PROP_URL = "url";

	static {
		try {
			Class.forName(READER.get(PROP_DRIVER_CLASS_NAME));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				logger.log(Level.DEBUG, "connection have been got");
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
