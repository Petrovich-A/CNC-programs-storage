package by.petrovich.storage.service.impl;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.DaoProvider;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.hasher.impl.PasswordService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.UserService;
import by.petrovich.storage.validator.impl.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final UserDao userDao = daoProvider.getUserDao();
	private final UserValidator userValidator = UserValidator.getInstance();
	private final PasswordService passwordHasher = PasswordService.getInstance();

	@Override
	public Optional<User> authorizate(User userFromAuthorizationForm) throws ServiceException {
		Optional<User> userOptional = Optional.empty();
		try {
			userOptional = userDao.read(userFromAuthorizationForm.getLoginPersonnelNumber());
			User user = userOptional.get();
			if (user.getUserRole() != UserRole.ADMINISTRATOR) {
				user.setUserRole(UserRole.USER);
				userDao.updateRole(user);
			}
			userOptional = Optional.of(user);
			logger.log(Level.INFO, "user is authorized. user: {}", user.toString());
		} catch (DaoException e) {
			logger.log(Level.ERROR, "user with LoginPersonnelNumber: {} can't be authorizate",
					userFromAuthorizationForm.getLoginPersonnelNumber(), e);
			throw new ServiceException(e);
		}
		return userOptional;
	}

	@Override
	public void registrate(User userFromRegistrForm) throws ServiceException {
		String passwordHashed = null;
		try {
			passwordHashed = passwordHasher.generateHash(userFromRegistrForm.getPassword());
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.ERROR, "Can't find algorithm for password hashing.", e);
			throw new ServiceException(e);
		} catch (InvalidKeySpecException e) {
			logger.log(Level.ERROR, "Can't find valid key for password hashing.", e);
			throw new ServiceException(e);
		}
		userFromRegistrForm.setPassword(passwordHashed);
		userFromRegistrForm.setUserRole(UserRole.GUEST);
		try {
			userDao.create(userFromRegistrForm);
		} catch (DaoException e2) {
			logger.log(Level.ERROR, "User can't be registred, user: {}", userFromRegistrForm, e2);
			throw new ServiceException(e2);
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
	public Optional<User> readUserByloginPersonnelNumber(int loginPersonnelNumber) throws ServiceException {
		Optional<User> userOptional = Optional.empty();
		try {
			userOptional = userDao.read(loginPersonnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't find user by loginPersonnelNumber: {}", loginPersonnelNumber, e);
			throw new ServiceException(e);
		}
		return userOptional;
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
	public List<User> readAllUsers() throws ServiceException {
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
		boolean isUserValid = userValidator.isUserValid(userFromRegistrForm);
		if (!isUserValid) {
			logger.log(Level.ERROR, "User from registration's form isn't valid. User: {}",
					userFromRegistrForm.toString());
		}
		return isUserValid;
	}

	@Override
	public boolean isExist(User userDataForm) throws ServiceException {
		boolean isExist = false;
		try {
			isExist = userDao.isUserExist(userDataForm.getLoginPersonnelNumber());
		} catch (DaoException e) {
			throw new ServiceException(String.format("Can't do isUserExist.", e));
		}
		if (isExist) {
			logger.log(Level.INFO, "User with loginPersonnelNumber: {} is existed in DB.",
					userDataForm.getLoginPersonnelNumber());
		}
		return isExist;
	}

	@Override
	public boolean isUsersLoginAndIsPasswordMatch(User userFromAuthorizationForm) {
		Optional<User> userOptional = Optional.empty();
		boolean isUsersLoginsAndPasswordsMatch = false;
		boolean isLoginMatch = false;
		boolean isPasswordMatch = false;
		User userFromDB = null;
		try {
			userOptional = userDao.read(userFromAuthorizationForm.getLoginPersonnelNumber());
			userFromDB = userOptional.get();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isLoginMatch = isloginPersonnelNumberMatch(userFromAuthorizationForm.getLoginPersonnelNumber(),
				userFromDB.getLoginPersonnelNumber());
		isPasswordMatch = isPaswordMatch(userFromAuthorizationForm.getPassword(), userFromDB.getPassword());
		if (isLoginMatch && isPasswordMatch) {
			isUsersLoginsAndPasswordsMatch = true;
		}
		return isUsersLoginsAndPasswordsMatch;
	}

	private boolean isloginPersonnelNumberMatch(int loginPersonnelNumber, int loginPersonnelNumberMatch) {
		boolean isloginPersonnelNumbersMatch = false;
		if (loginPersonnelNumber == loginPersonnelNumberMatch) {
			isloginPersonnelNumbersMatch = true;
		}
		return isloginPersonnelNumbersMatch;
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

	@SuppressWarnings("unused")
	private boolean loginPasswordValidate(int loginPersonnelNumber, String password) throws ServiceException {
		if (!userValidator.isLoginPersonnelNumberValid(String.valueOf(loginPersonnelNumber))
				&& !userValidator.isPasswordValid(password)) {
			logger.log(Level.ERROR, "login: {} and password: {} isn't valid", loginPersonnelNumber, password);// to do
		}
		return true;
	}

}
