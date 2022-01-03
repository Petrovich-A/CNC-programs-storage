package by.petrovich.storage.service;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.entity.User;

import java.util.List;

public interface UserService {

    void registration(User user) throws ServiceException;

    User authorization(User user) throws ServiceException;

    boolean validation(User user) throws ServiceException;


}
