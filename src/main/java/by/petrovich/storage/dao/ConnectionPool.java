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
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CncProgramPool");
            connection = dataSource.getConnection();
            logger.log(Level.DEBUG, "connection have been got", dataSource);
        } catch (NamingException e) {
            throw new RuntimeException("Naming problem", e);
        } catch (SQLException e) {
            throw new RuntimeException("Connection isn't available", e);
        }
        return connection;
    }


}
