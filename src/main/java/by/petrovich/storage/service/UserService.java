package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.User;

public interface UserService {
	List<User> readAll() throws ServiceException;

	User read(int loginPersonnelNumber) throws ServiceException;

	void delete(int loginPersonnelNumber) throws ServiceException;

	void update(User user, int loginPersonnelNumber) throws ServiceException;

	void register(User userFromRegistrForm) throws ServiceException;

	User authorize(User userFromAuthorForm) throws ServiceException;

	boolean authorizValidate(int loginPersonnelNumber, String password) throws ServiceException;

	boolean userValidate(User userFromRegistrForm) throws ServiceException;

}
