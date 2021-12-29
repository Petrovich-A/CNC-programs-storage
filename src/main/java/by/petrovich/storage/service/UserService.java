package by.petrovich.storage.service;

import by.petrovich.storage.entity.User;

public interface UserService {
    void registration(User user) throws ServiceException;

    User authorization(User user) throws ServiceException;

    boolean validation(User user) throws ServiceException;

}
