package by.petrovich.storage.service;

import by.petrovich.storage.entity.User;

public interface UserService {

    void register(User userFromRegistrForm) throws ServiceException;

    User authorize(User userFromAuthorizForm) throws ServiceException;

    boolean validate(User user) throws ServiceException;


}
