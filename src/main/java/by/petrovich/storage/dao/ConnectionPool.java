package by.petrovich.storage.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
	private final static Logger logger = LogManager.getLogger();

	private ConnectionPool() {
	}

	private static ConnectionPool instance = null;

	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public Connection getConnection() {
		InitialContext initialContext = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			initialContext = new InitialContext();
			dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/CncProgramPool"); 
			connection = dataSource.getConnection();
			logger.log(Level.DEBUG, "connection have been got", connection);
		} catch (NamingException e) {
//			throw new RuntimeException("Naming problem", e);
			e.printStackTrace();
		} catch (SQLException e) {
//			throw new RuntimeException("Connection isn't available", e);
			e.printStackTrace();
		}
		return connection;
	}

}
