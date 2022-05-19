package by.petrovich.storage.dao;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.User;

/**
 * @author Petrovich A.V.
 *
 */
public interface UserDao {
	/**
	 * @return
	 * @throws DaoException
	 */
	List<User> readAllUsers() throws DaoException;

	/**
	 * @param personnelNumber
	 * @return
	 * @throws DaoException
	 */
	Optional<User> readUserByPersonnelNumber(int personnelNumber) throws DaoException;

	/**
	 * @param registrationUserInfo
	 * @throws DaoException
	 */
	void createUser(RegistrationUserInfo registrationUserInfo) throws DaoException;

	/**
	 * @param user
	 * @param personnelNumber
	 * @throws DaoException
	 */
	void update(User user, int personnelNumber) throws DaoException;

	/**
	 * @param user
	 * @throws DaoException
	 */
	void updateUserRole(User user) throws DaoException;

	/**
	 * @param personnelNumber
	 * @return
	 * @throws DaoException
	 */
	boolean isUserExist(int personnelNumber) throws DaoException;
}
