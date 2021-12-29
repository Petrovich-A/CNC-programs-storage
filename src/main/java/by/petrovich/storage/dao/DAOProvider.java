package by.petrovich.storage.dao;

import by.petrovich.storage.dao.impl.UserDaoImpl;

public class DAOProvider {
    private static final DAOProvider INSTANCE = new DAOProvider();
    private final UserDao userDao = new UserDaoImpl();

    public DAOProvider() {
    }

    public static DAOProvider getInstance(){
        return INSTANCE;
    }

    public UserDao getUserDao(){
        return userDao;
    }
}
