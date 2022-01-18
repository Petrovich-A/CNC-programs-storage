package by.petrovich.storage.service;

import by.petrovich.storage.entity.User;

public interface UserService {

    void register(User userFromRegistrForm) throws ServiceException;

    User authorize(User userFromAuthorForm) throws ServiceException;

    boolean validate(int loginPersonnelNumber, String password) throws ServiceException;


}
