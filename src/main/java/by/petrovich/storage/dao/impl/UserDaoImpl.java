package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.ColumnName;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.dao.pool.ConnectionPool;
import by.petrovich.storage.dao.pool.StandardConnectionPool;
import by.petrovich.storage.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger();
	private final String SQL_FIND_ALL = "SELECT login_personnel_number, password, employee_name, employee_surname, "
			+ "employee_patronymic, position, email, create_time, user_role_id FROM users";
	private final String SQL_CREATE = "INSERT INTO users(login_personnel_number, password, employee_name, "
			+ "employee_surname, employee_patronymic, position, email, create_time, users_roles_user_role_id) VALUES(?,?,?,?,?,?,?,?,?)";
	private final String SQL_READ = "SELECT login_personnel_number, password, employee_name,"
			+ " employee_surname, employee_patronymic, position, email, create_time, user_role_id "
			+ "FROM users WHERE user_id= ?";
	private final String SQL_DELETE = "DELETE FROM users login_personnel_number = ?, password = ?, employee_name = ?,"
			+ " employee_surname = ?, employee_patronymic = ?, position = ?, email = ?, create_time = ?, users_roles_user_role_id = ?"
			+ " WHERE user_id = ?";
	private final String SQL_UPDATE = "UPDATE users SET  WHERE user_id = ?";
	private final String SQL_FIND_BY_LOGIN_PERSONNEL_NUMBER = "SELECT login_personnel_number, password, employee_name, "
			+ "employee_surname, employee_patronymic, position, email, create_time, user_role_id FROM users WHERE loginPersonnelNumber = ?";

	@Override
	public List<User> findAll() throws DaoException {
		List<User> allUsers = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
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
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted);
            logger.log(Level.DEBUG, "create user have done", user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public User findUser(String loginPersonnelNumber) throws DaoException {
		User user = new User();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_FIND_BY_LOGIN_PERSONNEL_NUMBER);) {
			preparedStatement.setInt(1, user.getLoginPersonnelNumber());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmployeeName());
			preparedStatement.setString(4, user.getEmployeeSurname());
			preparedStatement.setString(5, user.getEmployeePatronymic());
			preparedStatement.setString(6, user.getPosition());
			preparedStatement.setString(7, user.getEmail());
			preparedStatement.setTimestamp(8, (Timestamp) user.getDate());
			preparedStatement.setInt(9, user.getUserRole().getValue()); // ???
			logger.log(Level.DEBUG, "user find by loginPersonnelNumber", user, loginPersonnelNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User read(int id) throws DaoException {
		User user = new User();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			preparedStatement.setInt(1, id);
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
	public void delete(User user) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
			preparedStatement.setInt(1, user.getId());
			logger.log(Level.DEBUG, "user with id {} is deleted", user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private User buildUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt(ColumnName.USER_ID));
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
