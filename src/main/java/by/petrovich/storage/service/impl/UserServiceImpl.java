package by.petrovich.storage.service.impl;

import by.petrovich.storage.dao.DAOException;
import by.petrovich.storage.dao.DAOProvider;
import by.petrovich.storage.dao.UserDao;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.UserService;

public class UserServiceImpl implements UserService {
    private final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
    private final UserDao USER_DAO = DAO_PROVIDER.getUserDao();

    @Override
    public void registration(User user) throws ServiceException {
        try {
            USER_DAO.create(user);
        } catch (DAOException e) {
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
