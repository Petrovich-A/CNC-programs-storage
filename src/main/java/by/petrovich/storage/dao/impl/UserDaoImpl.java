package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.ColumnNames;
import by.petrovich.storage.dao.pool.ConnectionPool;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final static Logger logger = LogManager.getLogger();
    private final String SQL_FIND_ALL_USERS = "SELECT login_personnel_number, password, employee_name, employee_surname, " +
            "employee_patronymic, position, email, create_time, user_role_id FROM users";
    private final String SQL_CREATE_USER = "INSERT INTO users(login_personnel_number, password, employee_name, " +
            "employee_surname, employee_patronymic, position, email, create_time, users_roles_user_role_id) VALUES(?,?,?,?,?,?,?,?,?)";
    private final String SQL_FIND_USER_BY_ID = "SELECT login_personnel_number, password, employee_name, employee_surname," +
            " employee_patronymic, position, email, create_time, user_role_id FROM users WHERE user_id=?";
    private final String SQL_READ_USER = "SELECT login_personnel_number, password, employee_name,"
            + " employee_surname, employee_patronymic, position, email, create_time, user_role_id "
            + "FROM users WHERE user_id= ?";
    private final String SQL_DELETE_USER = "DELETE FROM users login_personnel_number = ?, password = ?, employee_name = ?," +
            " employee_surname = ?, employee_patronymic = ?, position = ?, email = ?, create_time = ?, users_roles_user_role_id = ? WHERE user_id = ?";
    private final String SQL_UPDATE_USER = "UPDATE users SET  WHERE user_id = ?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allUsers.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void create(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER);) {
            preparedStatement.setInt(1, user.getLoginPersonnelNumber());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmployeeName());
            preparedStatement.setString(4, user.getEmployeeSurname());
            preparedStatement.setString(5, user.getEmployeePatronymic());
            preparedStatement.setString(6, user.getPosition());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setTimestamp(8, (Timestamp) user.getDate());
            preparedStatement.setInt(9, user.getUserRole().getValue()); // ???
            logger.log(Level.DEBUG, "create user have done", user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String findUserRole(int userId) throws DaoException {
        String userRole = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_USER_BY_ID);) {
            userRole = resultSet.getString(2);
            logger.log(Level.DEBUG, "user find by role", userRole);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRole;
    }

    @Override
    public User read(int userId) throws DaoException {
        User user = new User();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_USER);) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setLoginPersonnelNumber(resultSet.getInt(2));
                user.setPassword(resultSet.getString(3));
                user.setEmployeeName(resultSet.getString(4));
                user.setEmployeeSurname(resultSet.getString(5));
                user.setEmployeePatronymic(resultSet.getString(6));
                user.setPosition(resultSet.getString(7));
                user.setEmail(resultSet.getString(8));
                user.setDate(resultSet.getDate(9));
//  to do       user.getUserRole(UserRole.ofUserRole(resultSet.getInt(10)));
                logger.log(Level.DEBUG, "user is readed", user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);) {
            preparedStatement.setInt(1, user.getLoginPersonnelNumber());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmployeeName());
            preparedStatement.setString(4, user.getEmployeeSurname());
            preparedStatement.setString(5, user.getEmployeePatronymic());
            preparedStatement.setString(6, user.getPosition());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setTimestamp(8, (Timestamp) user.getDate());
            preparedStatement.setInt(9, user.getUserRole().getValue()); // ???
            logger.log(Level.DEBUG, "user is updated", user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User user) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);) {
            preparedStatement.setInt(1, user.getId());
            logger.log(Level.DEBUG, "user with id {} is deleted", user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ColumnNames.USER_ID));
        user.setLoginPersonnelNumber(resultSet.getInt(ColumnNames.LOGIN_PERSONNEL_NUMBER));
        user.setPassword(resultSet.getString(ColumnNames.PASSWORD));
        user.setEmployeeName(ColumnNames.EMPLOYEE_NAME);
        user.setEmployeeSurname(ColumnNames.EMPLOYEE_SURNAME);
        user.setEmployeePatronymic(ColumnNames.EMPLOYEE_PATRONYMIC);
        user.setPosition(ColumnNames.POSITION);
        user.setEmail(ColumnNames.EMAIL);
        user.setDate(new java.util.Date());
        user.setUserRole(UserRole.USER);
        logger.log(Level.DEBUG, "user build successfully", user.toString());
        return user;
    }

}
