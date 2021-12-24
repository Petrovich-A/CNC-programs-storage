package by.petrovich.storage.dao;

import by.petrovich.storage.entity.User;

public interface UserDao {

    void registration(User user) throws DAOException;

    User authorization(User user) throws DAOException;

    void update(User user) throws DAOException;

    void delete(User user) throws DAOException;

}
