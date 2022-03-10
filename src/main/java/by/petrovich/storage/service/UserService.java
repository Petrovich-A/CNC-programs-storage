package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.User;

public interface UserService {
	List<User> readAll() throws ServiceException;

	User read(int loginPersonnelNumber) throws ServiceException;

	void delete(int loginPersonnelNumber) throws ServiceException;

	void update(User user, int loginPersonnelNumber) throws ServiceException;

	void registrate(User userFromRegistrForm) throws ServiceException;

	void logOut(User user) throws ServiceException;

	User authorizate(User userFromAuthorForm) throws ServiceException;

	boolean loginPasswordValidate(int loginPersonnelNumber, String password) throws ServiceException;

	boolean isValid(User userFromRegistrForm);

	boolean isUserExist(User userFromRegistrForm) throws ServiceException;

}
