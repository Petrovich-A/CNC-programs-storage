package by.petrovich.storage.service.impl;

import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.UserService;

public class UserServiceImpl implements UserService {
//    private final
    @Override
    public void registration(User user) throws ServiceException {

    }

    @Override
    public User authorization(User user) throws ServiceException {
        return null;
    }

    @Override
    public boolean validation(User user) throws ServiceException {
        return false;
    }
}
