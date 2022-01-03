package by.petrovich.storage.dao;

import by.petrovich.storage.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static final DaoProvider INSTANCE = new DaoProvider();
    private final UserDao userDao = new UserDaoImpl();

    public DaoProvider() {
    }

    public static DaoProvider getInstance(){
        return INSTANCE;
    }

    public UserDao getUserDao(){
        return userDao;
    }
}
