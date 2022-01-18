package by.petrovich.storage.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.DaoProvider;
import by.petrovich.storage.dao.impl.UserDaoImpl;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.UserService;
import by.petrovich.storage.validator.impl.UserValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final UserDaoImpl userDaoImpl = daoProvider.getUserDaoImpl();
	private final UserValidator userValidator = new UserValidator();

	@Override
	public User authorize(User userFromAuthorForm) throws ServiceException {
		User userFromDao = null;
		if (validate(userFromAuthorForm.getLoginPersonnelNumber(), userFromAuthorForm.getPassword())) {
			try {
				userFromDao = userDaoImpl.findUser(userFromAuthorForm.getLoginPersonnelNumber());
				userFromDao.setUserRole(UserRole.USER);
				userDaoImpl.update(userFromDao, userFromDao.getId());
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
	public boolean validate(int loginPersonnelNumber, String password) throws ServiceException {
		if (!userValidator.isLoginPersonnelNumberValid(String.valueOf(loginPersonnelNumber))
				&& !userValidator.isPasswordValid(password)) {
			logger.log(Level.ERROR, "");// to do
		}
		return true;
	}

	@Override
	public void register(User userFromRegistrForm) throws ServiceException {
		try {
			userDaoImpl.create(userFromRegistrForm);
		} catch (DaoException e) {
			e.printStackTrace();// to do
		}

	}
}
