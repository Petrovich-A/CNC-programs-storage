package by.petrovich.storage.dao.impl;

import static by.petrovich.storage.dao.ColumnName.CREATE_TIME;
import static by.petrovich.storage.dao.ColumnName.EMAIL;
import static by.petrovich.storage.dao.ColumnName.EMPLOYEE_NAME;
import static by.petrovich.storage.dao.ColumnName.EMPLOYEE_PATRONYMIC;
import static by.petrovich.storage.dao.ColumnName.EMPLOYEE_SURNAME;
import static by.petrovich.storage.dao.ColumnName.PERSONNEL_NUMBER;
import static by.petrovich.storage.dao.ColumnName.PASSWORD;
import static by.petrovich.storage.dao.ColumnName.POSITION_NAME;
import static by.petrovich.storage.dao.ColumnName.ROLE_NAME;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.dao.connection.ConnectionPool;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_READ_ALL_USERS = """
			SELECT
				personnel_number, password, employee_name, employee_surname,
				employee_patronymic, email, create_time, role_name, position_name
					FROM users
						LEFT JOIN user_roles ON users.role_id = user_roles.role_id
						LEFT JOIN employee_positions
							ON users.position_id = employee_positions.position_id
			""";
	private static final String SQL_CREATE_USER = """
			INSERT INTO users
				(personnel_number, password, employee_name,employee_surname,
				employee_patronymic, email, create_time, role_id, position_id)
					VALUES(?,?,?,?,?,?,?,?,?)
			""";
	private static final String SQL_UPDATE_USER = """
			UPDATE users
				SET employee_name = ?, employee_surname = ?, employee_patronymic = ?,
			email = ?, role_id = ?, position_id = ?
				WHERE personnel_number = ?
			""";
	private static final String SQL_UPDATE_USER_ROLE = """
			UPDATE users SET role_id = ?
				WHERE personnel_number = ?
			""";
	private static final String SQL_READ_USER = """
			SELECT
				personnel_number, password, employee_name, employee_surname,
				employee_patronymic, email, create_time, role_name, position_name
					FROM users
						LEFT JOIN user_roles ON users.role_id = user_roles.role_id
						LEFT JOIN employee_positions ON users.position_id = employee_positions.position_id
							WHERE personnel_number = ?
			""";
	private static final String SQL_IS_EXIST_BY_LOGIN = """
			SELECT EXISTS
				(SELECT personnel_number FROM users WHERE personnel_number = ?)
			""";

	@Override
	public List<User> readAllUsers() throws DaoException {
		List<User> allUsers = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_ALL_USERS);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				allUsers.add(buildUser(resultSet));
			}
			logger.log(Level.INFO, "read allUsers from BD have done successfully. allUsers: {} ", allUsers.toString());
		} catch (SQLException e) {
			throw new DaoException(String.format("can't read allUsers from DB", e));
		}
		return allUsers;
	}

	@Override
	public void createUser(RegistrationUserInfo registrationUserInfo) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
			preparedStatement.setInt(1, registrationUserInfo.getPersonnelNumber());
			preparedStatement.setString(2, registrationUserInfo.getPassword());
			preparedStatement.setString(3, registrationUserInfo.getEmployeeName());
			preparedStatement.setString(4, registrationUserInfo.getEmployeeSurname());
			preparedStatement.setString(5, registrationUserInfo.getEmployeePatronymic());
			preparedStatement.setString(6, registrationUserInfo.getEmail());
			preparedStatement.setTimestamp(7, registrationUserInfo.get—reationDate());
			preparedStatement.setInt(8, registrationUserInfo.getUserRole().getOrdinalNumber());
			preparedStatement.setInt(9, registrationUserInfo.getEmployeePosition().getOrdinalNumber());
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "User creating from BD have done successfully. user: {} ",
					registrationUserInfo.toString());
		} catch (SQLException e) {
			throw new DaoException(String.format("can't create user to DB. user: %s ", registrationUserInfo.toString()),
					e);
		}
	}

	@Override
	public Optional<User> readUserByPersonnelNumber(int personnelNumber) throws DaoException {
		Optional<User> userOptional = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_USER)) {
			preparedStatement.setInt(1, personnelNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = buildUser(resultSet);
				userOptional = Optional.ofNullable(user);
			}
		} catch (SQLException e) {
			throw new DaoException(
					String.format("—an't read user with personnelNumber: %s from DB", personnelNumber, e));
		}
		logger.log(Level.INFO, "User reading from BD have done successfully. userFromDao: {}", userOptional.toString());
		return userOptional.isPresent() ? userOptional : Optional.empty();
	}

	@Override
	public void update(User user, int personnelNumber) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
			preparedStatement.setString(1, user.getEmployeeName());
			preparedStatement.setString(2, user.getEmployeeSurname());
			preparedStatement.setString(3, user.getEmployeePatronymic());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setInt(5, user.getUserRole().getOrdinalNumber());
			preparedStatement.setInt(6, user.getEmployeePosition().getOrdinalNumber());
			preparedStatement.setInt(7, personnelNumber);
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "user is updated. user: {}", user.toString());
		} catch (SQLException e) {
			throw new DaoException(String.format("can't update user with personnelNumber: %s in DB. User: %s ",
					personnelNumber, user.toString(), e));
		}
	}

	@Override
	public boolean isUserExist(int personnelNumber) throws DaoException {
		boolean isExist = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_EXIST_BY_LOGIN,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			preparedStatement.setInt(1, personnelNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isExist = resultSet.getBoolean(1);
			}
		} catch (SQLException e) {
			throw new DaoException(
					String.format("Can't do isExist. SQL_IS_EXIST_BY_LOGIN: %s", SQL_IS_EXIST_BY_LOGIN, e));
		}
		logger.log(Level.INFO, "isExists: {}", isExist);
		return isExist;
	}

	@Override
	public void updateUserRole(User user) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_ROLE)) {
			preparedStatement.setInt(1, user.getUserRole().getOrdinalNumber());
			preparedStatement.setInt(2, user.getPersonnelNumber());
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "User's role is updated. user: {}", user.toString());
		} catch (SQLException e) {
			throw new DaoException(String.format("can't update user's role. User: %s ", user.toString(), e));
		}
	}

	private User buildUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER));
		user.setPassword(resultSet.getString(PASSWORD));
		user.setEmployeeName(resultSet.getString(EMPLOYEE_NAME));
		user.setEmployeeSurname(resultSet.getString(EMPLOYEE_SURNAME));
		user.setEmployeePatronymic(resultSet.getString(EMPLOYEE_PATRONYMIC));
		user.setEmail(resultSet.getString(EMAIL));
		user.setCreationDate(resultSet.getTimestamp(CREATE_TIME));
		user.setUserRole(UserRole.fromString(resultSet.getString(ROLE_NAME)));
		user.setEmployeePosition(EmployeePosition.fromString(resultSet.getString(POSITION_NAME)));
		logger.log(Level.INFO, "user from DB is built successfully: {}", user.toString());
		return user;
	}

}
