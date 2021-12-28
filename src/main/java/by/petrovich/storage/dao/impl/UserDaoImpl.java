package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.ConnectionPool;
import by.petrovich.storage.dao.DAOException;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private final static Logger logger = LogManager.getLogger();
    private final String SQL_ADD_USER = "INSERT INTO users(user_id, login_personnel_number, password, employee_name, " +
            "employee_surname, employee_patronymic, position, email, create_time, user_role_id) VALUES(?,?,?,?,?,?,?,?,?)";
    private final String SQL_GET_USER_ROLE = "SELECT user_role_name FROM users_roles WHERE user_role_id=?";
    private final String SQL_READ_USER = "SELECT login_personnel_number, password, employee_name, employee_surname," +
            " employee_patronymic, position, email, create_time, user_role_id FROM users WHERE user_id= ?";

    @Override
    public void create(User user) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER);) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getLoginPersonnelNumber());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmployeeName());
            preparedStatement.setString(5, user.getEmployeeSurname());
            preparedStatement.setString(6, user.getEmployeePatronimic());
            preparedStatement.setString(7, user.getPosition());
            preparedStatement.setDate(8, (Date) user.getDate());
            preparedStatement.setInt(9, user.getUserRole().getValue());  // ???
            logger.log(Level.DEBUG, "create user have done", user.toString());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getUserRole(int userId) throws DAOException {
        String userRole = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_USER_ROLE);) {
            userRole = resultSet.getString(2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userRole;
    }

    @Override
    public User read(int userId) throws DAOException {
        User user = new User();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER);) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user.setLoginPersonnelNumber(resultSet.getInt(2));
                user.setPassword(resultSet.getString(3));
                user.setEmployeeName(resultSet.getString(4));
                user.setEmployeeSurname(resultSet.getString(5));
                user.setEmployeePatronimic(resultSet.getString(6));
                user.setPosition(resultSet.getString(7));
                user.setEmail(resultSet.getString(8));
                user.setDate(resultSet.getDate(9));
//                user.getUserRole(UserRole.ofUserRole(resultSet.getInt(10)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public void delete(User user) throws DAOException {

    }


}
