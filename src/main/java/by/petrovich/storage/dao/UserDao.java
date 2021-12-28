package by.petrovich.storage.dao;

import by.petrovich.storage.entity.User;

public interface UserDao {

    void create(User user) throws DAOException;

    User read(int userId) throws DAOException;

    void update(User user) throws DAOException;

    void delete(User user) throws DAOException;

    String getUserRole(int userId) throws DAOException;

}
