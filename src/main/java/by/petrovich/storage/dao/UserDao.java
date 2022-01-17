package by.petrovich.storage.dao;

import by.petrovich.storage.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll() throws DaoException;

    void create(User user) throws DaoException;

    User read(int id) throws DaoException;

    void update(User user, int id) throws DaoException;

    void delete(User user) throws DaoException;

    User findUser(int loginPersonnelNumber) throws DaoException;

}
