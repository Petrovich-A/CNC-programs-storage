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
	public User authorizate(User userFromAuthorForm) throws ServiceException {
		User userFromDao = null;
		if (loginPasswordValidate(userFromAuthorForm.getLoginPersonnelNumber(), userFromAuthorForm.getPassword())) {
			try {
				userFromDao = userDao.read(userFromAuthorForm.getLoginPersonnelNumber());
				userFromDao.setUserRole(UserRole.USER);
				userDao.update(userFromDao, userFromDao.getLoginPersonnelNumber());
				logger.log(Level.DEBUG, "user is authorized", userFromDao.toString());
			} catch (DaoException e) {
				logger.log(Level.ERROR, "login or password don't miss", userFromAuthorForm.toString(),
						userFromDao.toString());
				throw new ServiceException(e);
			}
		}
		return userFromDao;
	}

	@Override
	public void registrate(User userFromRegistrForm) throws ServiceException {
		boolean isExists = false;
		if (userValidate(userFromRegistrForm)) {
			try {
				isExists = userDao.isExists(userFromRegistrForm.getLoginPersonnelNumber());
			} catch (DaoException e2) {
				logger.log(Level.ERROR, "user with getLoginPersonnelNumber: {} is exist in DB",
						userFromRegistrForm.getLoginPersonnelNumber(), e2);
				throw new ServiceException(e2);
			}
		}
		if (!isExists) {
			try {
				userDao.create(userFromRegistrForm);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "user can't be registred, user: {}", userFromRegistrForm, e);
				throw new ServiceException(e);
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
	public boolean userValidate(User userFromRegistrForm) throws ServiceException {
		UserValidator userValidator = UserValidator.getInstance();
		if (!userValidator.isUserValid(userFromRegistrForm)) {
			logger.log(Level.ERROR, "user from registration form: {} isn't valid", userFromRegistrForm.toString());
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
	public void delete(int loginPersonnelNumber) throws ServiceException {
		try {
			userDao.delete(loginPersonnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't delete user with loginPersonnelNumber: {}", loginPersonnelNumber, e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(User userFromUpdateForm, int loginPersonnelNumber) throws ServiceException {
		try {
			userDao.update(userFromUpdateForm, loginPersonnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't update user: {}", userFromUpdateForm, loginPersonnelNumber, e);
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

}
