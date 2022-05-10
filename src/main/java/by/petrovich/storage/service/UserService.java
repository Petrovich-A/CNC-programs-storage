package by.petrovich.storage.service;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.User;

public interface UserService {
	List<User> readAllUsers() throws ServiceException;

	Optional<User> readUserByloginPersonnelNumber(int loginPersonnelNumber) throws ServiceException;

	void update(User user, int loginPersonnelNumber) throws ServiceException;

	void registrate(RegistrationUserInfo registrationUserInfo) throws ServiceException;

	void logOut(User user) throws ServiceException;

	Optional<User> authorizate(int login, String password) throws ServiceException;

	boolean isExist(int personnelNumber) throws ServiceException;

	boolean isValid(RegistrationUserInfo registrationUserInfo);

	boolean isRegistrationUserInfoLoginAndPasswordMatchWtihUser(int login, String password) throws ServiceException;

}
