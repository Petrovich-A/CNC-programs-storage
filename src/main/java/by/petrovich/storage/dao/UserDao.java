package by.petrovich.storage.dao;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.User;

public interface UserDao {
	List<User> readAllUsers() throws DaoException;

	Optional<User> readUserByPersonnelNumber(int personnelNumber) throws DaoException;

	void createUser(RegistrationUserInfo registrationUserInfo) throws DaoException;

	void update(User user, int loginPersonnelNumber) throws DaoException;

	void updateUserRole(User user) throws DaoException;

	boolean isUserExist(int personnelNumber) throws DaoException;
}
