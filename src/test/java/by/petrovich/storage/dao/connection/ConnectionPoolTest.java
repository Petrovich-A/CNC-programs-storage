package by.petrovich.storage.dao.connection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.petrovich.storage.dao.DaoException;

public class ConnectionPoolTest {
	static ConnectionPool connectionPool;
	static ProxyConnection proxyConnection;

	@BeforeClass
	public static void setUpBeforeClass() throws DaoException {
		connectionPool = ConnectionPool.getInstance();
		proxyConnection = connectionPool.getConnection();
	}

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(proxyConnection);
		connectionPool.releaseConnection(proxyConnection);
	}

}
