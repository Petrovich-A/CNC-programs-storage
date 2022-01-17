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

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final UserDaoImpl userDaoImpl = daoProvider.getUserDaoImpl();

	@Override
	public User authorize(User userFromAuthorizForm) throws ServiceException {
		User userFromDao = null;
		try {
			userFromDao = userDaoImpl.findUser(userFromAuthorizForm.getLoginPersonnelNumber());
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		if (userFromAuthorizForm.getLoginPersonnelNumber() == userFromDao.getLoginPersonnelNumber()
				&& userFromAuthorizForm.getPassword().equals(userFromDao.getPassword())) {
			userFromDao.setUserRole(UserRole.USER);
			logger.log(Level.DEBUG, "user is authorized", userFromDao.toString());
		}else {
			
		}
		return userFromDao;
	}

	@Override
	public boolean validate(User user) throws ServiceException {
		return false;
	}

	@Override
	public void register(User userFromRegistrForm) throws ServiceException {
		try {
			userDaoImpl.create(userFromRegistrForm);
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}
}
