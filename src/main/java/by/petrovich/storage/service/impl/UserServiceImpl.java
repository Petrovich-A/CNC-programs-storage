package by.petrovich.storage.service.impl;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.DaoProvider;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.UserService;
import by.petrovich.storage.validator.impl.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final UserDao userDao = daoProvider.getUserDao();

	@Override
	public User authorizate(User userFromAuthorizationForm) throws ServiceException {
		User userFromDao = null;
		try {
			userFromDao = userDao.read(userFromAuthorizationForm.getLoginPersonnelNumber());
			if (userFromDao.getUserRole() != UserRole.ADMINISTRATOR) {
				userFromDao.setUserRole(UserRole.USER);
			}
			userDao.updateRole(userFromDao);
			logger.log(Level.INFO, "user is authorized. user: {}", userFromDao.toString());
		} catch (DaoException e) {
			logger.log(Level.ERROR, "user with LoginPersonnelNumber: {} can't be authorizate",
					userFromAuthorizationForm.getLoginPersonnelNumber(), e);
			throw new ServiceException(e);
		}
		return userFromDao;
	}

	@Override
	public void registrate(User userFromRegistrForm) throws ServiceException {
		boolean isUserExists = false;
		if (isValid(userFromRegistrForm)) {
			try {
				isUserExists = userDao.isUserExists(userFromRegistrForm.getLoginPersonnelNumber());
			} catch (DaoException e2) {
				logger.log(Level.ERROR, "user with getLoginPersonnelNumber: {} is existed in DB",
						userFromRegistrForm.getLoginPersonnelNumber(), e2);
				throw new ServiceException(e2);
			}
		}
		if (!isUserExists) {
			try {
				userFromRegistrForm.setUserRole(UserRole.GUEST);
				userDao.create(userFromRegistrForm);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "User can't be registred, user: {}", userFromRegistrForm, e);
				throw new ServiceException("dfdf", e);
			}
		}
	}

	@Override
	public void logOut(User user) throws ServiceException {
		if (user.getUserRole() != UserRole.ADMINISTRATOR) {
			user.setUserRole(UserRole.GUEST);
			try {
				userDao.updateRole(user);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "Can't update role. User: {}", user.toString(), e);
			}
		}
	}

	@Override
	public boolean loginPasswordValidate(int loginPersonnelNumber, String password) throws ServiceException {
		UserValidator userValidator = UserValidator.getInstance();
		if (!userValidator.isLoginPersonnelNumberValid(String.valueOf(loginPersonnelNumber))
				&& !userValidator.isPasswordValid(password)) {
			logger.log(Level.ERROR, "login: {} and password: {} isn't valid", loginPersonnelNumber, password);// to do
		}
		return true;
	}

	@Override
	public User read(int loginPersonnelNumber) throws ServiceException {
		User userFromDao = null;
		try {
			userFromDao = userDao.read(loginPersonnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't find user by loginPersonnelNumber: {}", loginPersonnelNumber, e);
			throw new ServiceException(e);
		}
		return userFromDao;
	}

	@Override
	public void update(User userFromUpdateForm, int loginPersonnelNumber) throws ServiceException {
		try {
			userDao.update(userFromUpdateForm, loginPersonnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't update user: {} with loginPersonnelNumber: {}", userFromUpdateForm,
					loginPersonnelNumber, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> readAll() throws ServiceException {
		List<User> allUsers = null;
		try {
			allUsers = userDao.readAll();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't read all users", e);
			throw new ServiceException(e);
		}
		return allUsers;
	}

	@Override
	public boolean isValid(User userFromRegistrForm) {
		UserValidator userValidator = UserValidator.getInstance();
		boolean isUserValid = userValidator.isUserValid(userFromRegistrForm);
		if (!isUserValid) {
			logger.log(Level.ERROR, "User from registration's form isn't valid. User: {}",
					userFromRegistrForm.toString());
		}
		return isUserValid;
	}

	@Override
	public boolean isUserExist(User userFromRegistrForm) throws ServiceException {
		boolean isUserExist = false;
		try {
			isUserExist = userDao.isUserExists(userFromRegistrForm.getLoginPersonnelNumber());
		} catch (DaoException e) {
			throw new ServiceException(String.format("Can't do isUserExist.", e));
		}
		if (isUserExist) {
			logger.log(Level.INFO, "User with loginPersonnelNumber: {} is existed in DB.",
					userFromRegistrForm.getLoginPersonnelNumber());
		}
		return isUserExist;
	}

}
