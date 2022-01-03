package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.ColumnNames;
import by.petrovich.storage.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.petrovich.storage.dao.ConnectionPool;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;

public class UserDaoImpl implements UserDao {
    private final static Logger logger = LogManager.getLogger();
    private final String SQL_FIND_ALL = "SELECT login_personnel_number, password, employee_name, employee_surname, " +
            "employee_patronymic, position, email, create_time, user_role_id FROM users";
    private final String SQL_CREATE_USER = "INSERT INTO users(user_id, login_personnel_number,"
            + " password, employee_name, employee_surname, employee_patronymic, position, email,"
            + " create_time, user_role_id) VALUES(?,?,?,?,?,?,?,?,?)";
    private final String SQL_FIND_USER_BY_ID = "SELECT login_personnel_number, password, employee_name, employee_surname," +
            " employee_patronymic, position, email, create_time, user_role_id FROM users WHERE user_id=?";
    private final String SQL_READ_USER = "SELECT login_personnel_number, password, employee_name,"
            + " employee_surname, employee_patronymic, position, email, create_time, user_role_id "
            + "FROM users WHERE user_id= ?";
    private final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";
    private String loginPersonnelNumber;
    private String password;
    private String employeeName;
    private String employeeSurname;
    private String employeePatronymic;
    private String position;
    private String email;

    @Override
    public List<User> findAll() throws DaoException {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {

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

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getLoginPersonnelNumber());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmployeeName());
            preparedStatement.setString(5, user.getEmployeeSurname());
            preparedStatement.setString(6, user.getEmployeePatronymic());
            preparedStatement.setString(7, user.getPosition());
            preparedStatement.setDate(8, (Date) user.getDate());
            preparedStatement.setInt(9, user.getUserRole().getValue()); // ???
            logger.log(Level.DEBUG, "create user have done", user.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserRole(int userId) throws DaoException {
        String userRole = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_USER_BY_ID);) {
            userRole = resultSet.getString(2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
//                user.getUserRole(UserRole.ofUserRole(resultSet.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException {

    }

    @Override
    public void delete(User user) throws DaoException {

    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ColumnNames.USER_ID));
        user.setLoginPersonnelNumber(resultSet.getInt(loginPersonnelNumber));
        user.setPassword(resultSet.getString(password));
        user.setEmployeeName(employeeName);
        user.setEmployeeSurname(employeeSurname);
        user.setEmployeePatronymic(employeePatronymic);
        user.setPosition(position);
        user.setEmail(email);
        user.setDate(new java.util.Date());
        user.setUserRole(UserRole.USER);
        return user;
    }

}
