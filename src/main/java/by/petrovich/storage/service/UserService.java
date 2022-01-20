package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.User;

public interface UserService {
	User read(int id) throws ServiceException;

	User find(int loginPersonnelNumber) throws ServiceException;

	List<User> readAll() throws ServiceException;

	void delete(int loginPersonnelNumber) throws ServiceException;

	void update(User user, int loginPersonnelNumber) throws ServiceException;

	void register(User userFromRegistrForm) throws ServiceException;

	User authorize(User userFromAuthorForm) throws ServiceException;

	boolean authorizValidate(int loginPersonnelNumber, String password) throws ServiceException;

	boolean registrValidate(User userFromRegistrForm) throws ServiceException;

}
