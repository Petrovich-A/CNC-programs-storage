package by.petrovich.storage.dao;

import by.petrovich.storage.entity.User;

import java.util.List;

public interface UserDao {
	List<User> readAll() throws DaoException;

	void create(User user) throws DaoException;

	User read(int loginPersonnelNumber) throws DaoException;

	void update(User user, int loginPersonnelNumber) throws DaoException;

	void updateRole(User user) throws DaoException;

	boolean isUserExist(int loginPersonnelNumber) throws DaoException;
}
