package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.ColumnName;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.dao.pool.ConnectionPool;
import by.petrovich.storage.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_READ_ALL = "SELECT login_personnel_number, password, employee_name, employee_surname, "
			+ "employee_patronymic, position, email, create_time, user_role_id FROM users";
	private static final String SQL_CREATE = "INSERT INTO users(login_personnel_number, password, employee_name, "
			+ "employee_surname, employee_patronymic, position, email, create_time, users_roles_user_role_id) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM users login_personnel_number = ?, password = ?, employee_name = ?,"
			+ " employee_surname = ?, employee_patronymic = ?, position = ?, email = ?, create_time = ?, users_roles_user_role_id = ?"
			+ " WHERE user_id = ?";
	private static final String SQL_UPDATE = "UPDATE users SET  WHERE user_id = ?";
	private static final String SQL_READ = "SELECT login_personnel_number, password, employee_name, employee_surname,"
			+ " employee_patronymic, position, email, create_time, user_role_id FROM users WHERE login_personnel_number = ?";

	@Override
	public List<User> readAll() throws DaoException {
		List<User> allUsers = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_ALL);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				allUsers.add(buildUser(resultSet));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "to do", e);
			throw new DaoException(e);
		}
		return allUsers;
	}

	@Override
	public void create(User user) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
			preparedStatement.setInt(1, user.getLoginPersonnelNumber());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmployeeName());
			preparedStatement.setString(4, user.getEmployeeSurname());
			preparedStatement.setString(5, user.getEmployeePatronymic());
			preparedStatement.setString(6, user.getPosition());
			preparedStatement.setString(7, user.getEmail());
			preparedStatement.setTimestamp(8, (Timestamp) user.getDate());
			preparedStatement.setInt(9, user.getUserRole().getValue()); // ???
			preparedStatement.executeUpdate();
			logger.log(Level.DEBUG, "create user have done", user.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User read(int loginPersonnelNumber) throws DaoException {
		User user = new User();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ);) {
			preparedStatement.setInt(1, loginPersonnelNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = buildUser(resultSet);
			}
			logger.log(Level.DEBUG, "user find by loginPersonnelNumber", user, loginPersonnelNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void update(User user, int id) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
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
	public void delete(int id) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
			preparedStatement.setInt(1, id);
			logger.log(Level.DEBUG, "user with id: {} is deleted", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private User buildUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setLoginPersonnelNumber(resultSet.getInt(ColumnName.LOGIN_PERSONNEL_NUMBER));
		user.setPassword(resultSet.getString(ColumnName.PASSWORD));
		user.setEmployeeName(resultSet.getString(ColumnName.EMPLOYEE_NAME));
		user.setEmployeeSurname(resultSet.getString(ColumnName.EMPLOYEE_SURNAME));
		user.setEmployeePatronymic(resultSet.getString(ColumnName.EMPLOYEE_PATRONYMIC));
		user.setPosition(resultSet.getString(ColumnName.POSITION));
		user.setEmail(resultSet.getString(ColumnName.EMAIL));
		user.setDate(new java.util.Date());
// to do       user.setUserRole(resultSet.getString(UserRole.USER));
		logger.log(Level.DEBUG, "user build successfully", user.toString());
		return user;
	}

}
