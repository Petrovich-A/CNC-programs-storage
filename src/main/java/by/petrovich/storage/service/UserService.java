package by.petrovich.storage.service;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.User;

/**
 * @author Petrovich A.V.
 *
 */
public interface UserService {
	/**
	 * @return
	 * @throws ServiceException
	 */
	List<User> readAllUsers() throws ServiceException;

	/**
	 * @param personnelNumber
	 * @return
	 * @throws ServiceException
	 */
	Optional<User> readUserByPersonnelNumber(int personnelNumber) throws ServiceException;

	/**
	 * @param user
	 * @param personnelNumber
	 * @throws ServiceException
	 */
	void update(User user, int personnelNumber) throws ServiceException;

	/**
	 * @param registrationUserInfo
	 * @throws ServiceException
	 */
	void registrateUser(RegistrationUserInfo registrationUserInfo) throws ServiceException;

	/**
	 * @param user
	 * @throws ServiceException
	 */
	void logOut(User user) throws ServiceException;

	/**
	 * @param login
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	Optional<User> authorizateUser(int login, String password) throws ServiceException;

	/**
	 * Checks is the transferred user exist in date base.
	 * 
	 * @param personnelNumber
	 * @return authorizated user from date base
	 * @throws ServiceException
	 */
	boolean isUserExist(int personnelNumber) throws ServiceException;

	/**
	 * Checks is registration information (data) from registration form valid.
	 * 
	 * @param registrationUserInfo
	 * @return true when user exists in base date
	 */
	boolean isValid(RegistrationUserInfo registrationUserInfo);

	/**
	 * Checks is the transferred login and password from authorization form is match
	 * with login and password from date base.
	 * 
	 * @param login
	 * @param password
	 * @return true when login and password match with login and password from date
	 *         base
	 * @throws ServiceException
	 */
	boolean isLoginAndPasswordMatchWithDateBaseData(int login, String password) throws ServiceException;

	/**
	 * Checks is the transferred login and password from authorization form valid.
	 * 
	 * @param login
	 * @param password
	 * @return true when both are valid
	 * @throws ServiceException
	 */
	boolean isLoginAndPasswordValid(int login, String password) throws ServiceException;

	/**
	 * Checks is the the transferred login and password from authorization form
	 * valid.
	 * 
	 * @param password
	 * @param confirmPassword
	 * @return true when login and password valid
	 * @throws ServiceException
	 */
	boolean isPasswordsMatch(String password, String confirmPassword) throws ServiceException;

}
