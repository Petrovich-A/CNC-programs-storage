package by.petrovich.storage.dao;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.User;

public interface UserDao {
	List<User> readAll() throws DaoException;

	Optional<User> read(int loginPersonnelNumber) throws DaoException;

	void create(RegistrationUserInfo registrationUserInfo) throws DaoException;

	void update(User user, int loginPersonnelNumber) throws DaoException;

	void updateRole(User user) throws DaoException;

	boolean isUserExist(int personnelNumber) throws DaoException;
}
