package by.petrovich.storage.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.DaoProvider;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.hasher.impl.PasswordService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.UserService;
import by.petrovich.storage.validator.impl.RegistrationUserInfoValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final UserDao userDao = daoProvider.getUserDao();
	private final RegistrationUserInfoValidator registrationUserInfoValidator = RegistrationUserInfoValidator
			.getInstance();
	private final PasswordService passwordHasher = PasswordService.getInstance();

	@Override
	public Optional<User> authorizateUser(int login, String password) throws ServiceException {
		Optional<User> userOptional = Optional.empty();
		try {
			userOptional = userDao.readUserByPersonnelNumber(login);
			User user = userOptional.get();
			if (user.getUserRole() != UserRole.ADMINISTRATOR) {
				user.setUserRole(UserRole.USER);
				userOptional = Optional.of(user);
				userDao.updateUserRole(user);
			}
			logger.log(Level.INFO, "User is authorized. user: {}", user.toString());
		} catch (DaoException e) {
			logger.log(Level.ERROR, "User with login: {} can't be authorizate", login, e);
			throw new ServiceException(e);
		}
		return userOptional;
	}

	@Override
	public void registrateUser(RegistrationUserInfo registrationUserInfo) throws ServiceException {
		String passwordHashed = null;
		try {
			passwordHashed = passwordHasher.generateHash(registrationUserInfo.getPassword());
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.ERROR, "Can't find algorithm for password hashing.", e);
			throw new ServiceException(e);
		} catch (InvalidKeySpecException e) {
			logger.log(Level.ERROR, "Can't find valid key for password hashing.", e);
			throw new ServiceException(e);
		}
//		registrationUserInfo.setPassword(passwordHashed);
		registrationUserInfo.setUserRole(UserRole.GUEST);
		try {
			userDao.createUser(registrationUserInfo);
		} catch (DaoException e2) {
			logger.log(Level.ERROR, "User can't be registred, user: {}", registrationUserInfo, e2);
			throw new ServiceException(e2);
		}
	}

	@Override
	public void logOut(User user) throws ServiceException {
		if (user.getUserRole() != UserRole.ADMINISTRATOR) {
			user.setUserRole(UserRole.GUEST);
			try {
				userDao.updateUserRole(user);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "Can't update role. User: {}", user.toString(), e);
			}
		}
	}

	@Override
	public Optional<User> readUserByPersonnelNumber(int personnelNumber) throws ServiceException {
		Optional<User> userOptional = Optional.empty();
		try {
			userOptional = userDao.readUserByPersonnelNumber(personnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't find user by personnelNumber: {}", personnelNumber, e);
			throw new ServiceException(e);
		}
		return userOptional;
	}

	@Override
	public void update(User user, int personnelNumber) throws ServiceException {
		try {
			userDao.update(user, personnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't update user: {} with personnelNumber: {}", user, personnelNumber,
					e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> readAllUsers() throws ServiceException {
		List<User> allUsers = null;
		try {
			allUsers = userDao.readAllUsers();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't read all users", e);
			throw new ServiceException(e);
		}
		return allUsers;
	}

	@Override
	public boolean isValid(RegistrationUserInfo registrationUserInfo) {
		boolean isValid = registrationUserInfoValidator.isRegistrationUserInfoValid(registrationUserInfo);
		if (!isValid) {
			logger.log(Level.ERROR,
					"Registration user info from registration's form isn't valid. RegistrationUserInfo: {}",
					registrationUserInfo.toString());
		}
		return isValid;
	}

	@Override
	public boolean isUserExist(int personnelNumber) throws ServiceException {
		boolean isExist = false;
		try {
			isExist = userDao.isUserExist(personnelNumber);
			if (isExist) {
				logger.log(Level.INFO, "User with personnelNumber: {} is existed in DB.", personnelNumber);
			}
		} catch (DaoException e) {
			throw new ServiceException(String.format("Can't do isUserExist.", e));
		}
		return isExist;
	}

	@Override
	public boolean isLoginAndPasswordMatch(int login, String password)
			throws ServiceException {
		Optional<User> userOptional = Optional.empty();
		boolean isUsersLoginsAndPasswordsMatch = false;
		boolean isLoginMatch = false;
		boolean isPasswordMatch = false;
		User user = new User();
		try {
			userOptional = userDao.readUserByPersonnelNumber(login);
			user = userOptional.get();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't read user with personnel number: {}", login, e);
			throw new ServiceException(e);
		}
		isLoginMatch = isPersonnelNumberMatch(user.getPersonnelNumber(), login);
		isPasswordMatch = isPaswordMatch(user.getPassword(), password);
		if (isLoginMatch && isPasswordMatch) {
			isUsersLoginsAndPasswordsMatch = true;
		}
		return isUsersLoginsAndPasswordsMatch;
	}

	private boolean isPersonnelNumberMatch(int personnelNumber, int personnelNumberMatch) {
		boolean isPersonnelNumbersMatch = false;
		if (personnelNumber == personnelNumberMatch) {
			isPersonnelNumbersMatch = true;
		}
		return isPersonnelNumbersMatch;
	}

	private boolean isPaswordMatch(String password, String passwordMatch) {
		String passwordHashed = null;
		boolean isPasswordMatch = false;
		try {
			passwordHashed = passwordHasher.generateHash(password);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (passwordHashed.equals(passwordMatch)) {
			isPasswordMatch = true;
		}
		return isPasswordMatch;
	}

	private boolean isLoginAndPasswordValid(int login, String password) throws ServiceException {
		if (!registrationUserInfoValidator.isPersonnelNumberValid(String.valueOf(login))
				&& !registrationUserInfoValidator.isPasswordValid(password)) {
			logger.log(Level.ERROR, "login: {} and password: {} isn't valid", login, password);// to do
		}
		return true;
	}

}
