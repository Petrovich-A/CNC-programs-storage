package by.petrovich.storage.service;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.User;

public interface UserService {
	List<User> readAllUsers() throws ServiceException;

	Optional<User> readUserByPersonnelNumber(int personnelNumber) throws ServiceException;

	void update(User user, int personnelNumber) throws ServiceException;

	void registrateUser(RegistrationUserInfo registrationUserInfo) throws ServiceException;

	void logOut(User user) throws ServiceException;

	Optional<User> authorizateUser(int login, String password) throws ServiceException;

	boolean isUserExist(int personnelNumber) throws ServiceException;

	boolean isValid(RegistrationUserInfo registrationUserInfo);

	boolean isLoginAndPasswordMatchWithDateBaseData(int login, String password) throws ServiceException;

	boolean isLoginAndPasswordValid(int login, String password) throws ServiceException;

	boolean isPasswordsMatch(String password, String confirmPassword) throws ServiceException;

}
