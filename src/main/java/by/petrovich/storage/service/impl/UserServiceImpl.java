package by.petrovich.storage.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.DaoProvider;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.UserService;
import by.petrovich.storage.validator.impl.UserValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final UserDao userDao = daoProvider.getUserDao();
	private final UserValidator userValidator = new UserValidator();

	@Override
	public User authorize(User userFromAuthorForm) throws ServiceException {
		User userFromDao = null;
		if (authorizValidate(userFromAuthorForm.getLoginPersonnelNumber(), userFromAuthorForm.getPassword())) {
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
	public void register(User userFromRegistrForm) throws ServiceException {
		if (registrValidate(userFromRegistrForm)) {
			try {
				userDao.create(userFromRegistrForm);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "", userFromRegistrForm, e);
			}
		}
	}

	@Override
	public boolean authorizValidate(int loginPersonnelNumber, String password) throws ServiceException {
		if (!userValidator.isLoginPersonnelNumberValid(String.valueOf(loginPersonnelNumber))
				&& !userValidator.isPasswordValid(password)) {
			logger.log(Level.ERROR, "login: {} and password: {} is't valid", loginPersonnelNumber, password);// to do
		}
		return true;
	}

	@Override
	public boolean registrValidate(User userFromRegistrForm) throws ServiceException {
		if (!userValidator.isUserValid(userFromRegistrForm)) {
			logger.log(Level.ERROR, "user from registration form: {} is't valid", userFromRegistrForm.toString());
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
		}
		return userFromDao;
	}

	@Override
	public void delete(int loginPersonnelNumber) throws ServiceException {
		try {
			userDao.delete(loginPersonnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't delete user with loginPersonnelNumber: {}", loginPersonnelNumber, e);
		}
	}

	@Override
	public void update(User userFromUpdateForm, int loginPersonnelNumber) throws ServiceException {
		try {
			userDao.update(userFromUpdateForm, loginPersonnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't update user: {}", userFromUpdateForm, loginPersonnelNumber, e);
		}
	}

	@Override
	public List<User> readAll() throws ServiceException {
		List<User> allUsers = null;
		try {
			allUsers = userDao.readAll();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't read all users", e);
		}
		return allUsers;
	}

}
