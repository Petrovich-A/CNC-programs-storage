package by.petrovich.storage.dao.pool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class StandardConnectionPool {
	private StandardConnectionPool() {
	}

	private static StandardConnectionPool instance = null;

	public static StandardConnectionPool getInstance() {
		if (instance == null) {
			instance = new StandardConnectionPool();
		}
		return instance;
	}

	public static Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			Context initContext = new InitialContext();
			dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/CncProgramPool");
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	

}
