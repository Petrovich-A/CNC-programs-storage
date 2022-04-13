package by.petrovich.storage.controller.listner;

import by.petrovich.storage.dao.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListner implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ConnectionPool.getInstance();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPool.getInstance().destroyPool();
	}

}
