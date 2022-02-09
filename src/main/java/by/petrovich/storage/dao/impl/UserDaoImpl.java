package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.ColumnName;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.dao.connection.ConnectionPool;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_READ_ALL = "SELECT login_personnel_number, password, employee_name, employee_surname, employee_patronymic,"
			+ " email, create_time, role_name, position_name FROM users LEFT JOIN user_roles ON role_id = user_role LEFT JOIN employee_positions ON position_id = employee_position";
	private static final String SQL_CREATE = "INSERT INTO users(login_personnel_number, password, employee_name, "
			+ "employee_surname, employee_patronymic, email, create_time, user_role_id, employee_positions_id) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM users login_personnel_number = ?, password = ?, employee_name = ?,"
			+ " employee_surname = ?, employee_patronymic = ?, position = ?, email = ?, create_time = ?, users_roles_user_role_id = ?"
			+ " WHERE user_id = ?";
	private static final String SQL_UPDATE = "UPDATE users SET WHERE user_id = ?";
	private static final String SQL_READ = "SELECT login_personnel_number, password, employee_name, employee_surname,"
			+ " employee_patronymic, position, email, create_time, user_role_id FROM users WHERE login_personnel_number = ?";
	private static final String SQL_READ_JOIN = "SELECT login_personnel_number, password, employee_name, employee_surname,"
			+ " employee_patronymic, position, email, create_time, user_role_id FROM users "
			+ "LEFT JOIN users_roles ON users.users_roles_user_role_id = users_roles.user_role_id WHERE login_personnel_number = ?";
	private static final String SQL_IS_EXIST = "SELECT EXISTS(SELECT login_personnel_number FROM users WHERE login_personnel_number = ?)";

	@Override
	public List<User> readAll() throws DaoException {
		List<User> allUsers = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_ALL);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				allUsers.add(buildUserFromDB(resultSet));
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
			preparedStatement.setString(6, user.getEmail());
			preparedStatement.setTimestamp(7, user.getCreationDate());
			preparedStatement.setInt(8, user.getUserRole().getOrdinalNumber());
			preparedStatement.setInt(9, user.getEmployeePosition().getOrdinalNumber());
			preparedStatement.executeUpdate();
			logger.log(Level.DEBUG, "DB user creating have done. user: {} ", user.toString());
		} catch (SQLException e) {
			logger.log(Level.DEBUG, "can't save user to DB. user: {} ", user.toString());
			throw new DaoException(e);
		}
	}

	@Override
	public User read(int loginPersonnelNumber) throws DaoException {
		User user = new User();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			preparedStatement.setInt(1, loginPersonnelNumber);
			while (resultSet.next()) {
				user = buildUserFromDB(resultSet);
			}
			logger.log(Level.DEBUG, "can't read user with loginPersonnelNumber: {}", loginPersonnelNumber);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return user;
	}

	@Override
	public void update(User user, int loginPersonnelNumber) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
			preparedStatement.setInt(1, user.getLoginPersonnelNumber());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmployeeName());
			preparedStatement.setString(4, user.getEmployeeSurname());
			preparedStatement.setString(5, user.getEmployeePatronymic());
			preparedStatement.setInt(6, user.getEmployeePosition().ordinal());
			preparedStatement.setString(7, user.getEmail());
			preparedStatement.setTimestamp(8, user.getCreationDate());
			preparedStatement.setInt(9, user.getUserRole().getOrdinalNumber());
			logger.log(Level.DEBUG, "user is updated", user.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(int loginPersonnelNumber) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
			preparedStatement.setInt(1, loginPersonnelNumber);
			logger.log(Level.DEBUG, "user with id: {} is deleted", loginPersonnelNumber);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public boolean isExists(int loginPersonnelNumber) throws DaoException {
		boolean isExist = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_EXIST,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			preparedStatement.setInt(1, loginPersonnelNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isExist = resultSet.getBoolean(1);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Can't do isExists? SQL_IS_EXIST: {}", SQL_IS_EXIST, e);
			throw new DaoException(e);
		}
		logger.log(Level.DEBUG, "isExists: {}", isExist);
		return isExist;
	}

	private User buildUserFromDB(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setLoginPersonnelNumber(resultSet.getInt(ColumnName.LOGIN_PERSONNEL_NUMBER));
		user.setPassword(resultSet.getString(ColumnName.PASSWORD));
		user.setEmployeeName(resultSet.getString(ColumnName.EMPLOYEE_NAME));
		user.setEmployeeSurname(resultSet.getString(ColumnName.EMPLOYEE_SURNAME));
		user.setEmployeePatronymic(resultSet.getString(ColumnName.EMPLOYEE_PATRONYMIC));
		user.setEmail(resultSet.getString(ColumnName.EMAIL));
		user.setCreationDate(resultSet.getTimestamp(ColumnName.CREATE_TIME));
		user.setUserRole(UserRole.fromString(resultSet.getString(ColumnName.ROLE_NAME)));
		user.setEmployeePosition(EmployeePosition.fromString(resultSet.getString(ColumnName.POSITION_NAME)));
		logger.log(Level.DEBUG, "user from DB is built successfully", user.toString());
		return user;
	}

}
