package by.petrovich.storage.dao;

import by.petrovich.storage.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll() throws DaoException;

    void create(User user) throws DaoException;

    User read(int userId) throws DaoException;

    void update(User user) throws DaoException;

    void delete(User user) throws DaoException;

    String findUserRole(int userId) throws DaoException;

}
