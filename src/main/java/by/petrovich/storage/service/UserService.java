package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.User;

public interface UserService {
	List<User> readAllUsers() throws ServiceException;

	User readUserByloginPersonnelNumber(int loginPersonnelNumber) throws ServiceException;

	void update(User user, int loginPersonnelNumber) throws ServiceException;

	void registrate(User userFromRegistrForm) throws ServiceException;

	void logOut(User user) throws ServiceException;

	User authorizate(User userFromAuthorForm) throws ServiceException;

	boolean isValid(User userFromRegistrForm);

	boolean isExist(User userFromRegistrForm) throws ServiceException;

	public boolean isUsersLoginAndIsPasswordMatch(User userFromAuthorizationForm) throws ServiceException;

}
