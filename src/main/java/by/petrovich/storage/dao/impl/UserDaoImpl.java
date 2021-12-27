package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.ConnectionPool;
import by.petrovich.storage.dao.DAOException;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private final String SQL_ADD = "INSERT INTO users(user_id, login_personnel_number, password, employee_name, " +
            "employee_surname, employee_patronymic, position, email, create_time, user_role_id) VALUES(?,?,?,?,?,?,?,?,?)";

    private final String SQL_AUTHORIZATION = "SELECT * FROM user WHERE name= ? AND password = ?";

    @Override
    public void create(User user) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD);) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getLoginPersonnelNumber());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmployeeName());
            preparedStatement.setString(5, user.getEmployeeSurname());
            preparedStatement.setString(6, user.getEmployeePatronimic());
            preparedStatement.setString(7, user.getPosition());
            preparedStatement.setDate(8, (Date) user.getDate());
//            preparedStatement.setInt(9, UserRole.);  // ???

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public User read(User user) throws DAOException {
        return null;
    }

    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public void delete(User user) throws DAOException {

    }
}
