package by.petrovich.storage.service.impl;

import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.DaoProvider;
import by.petrovich.storage.dao.impl.UserDaoImpl;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.UserService;

public class UserServiceImpl implements UserService {
    private final DaoProvider daoProvider = DaoProvider.getInstance();
    private final UserDaoImpl userDaoImpl = daoProvider.getUserDaoImpl();

    @Override
    public void registration(User user) throws ServiceException {
        try {
            userDaoImpl.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
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
