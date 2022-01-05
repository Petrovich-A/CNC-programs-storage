package by.petrovich.storage.dao.pool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class StandardConnectionPool {
    private static final String DATASOURSE_NAME = "jdbc/CncProgramPool";
    private static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env/jdbc/CncProgramPool");
            dataSource = (DataSource) envContext.lookup(DATASOURSE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private StandardConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
