package by.petrovich.storage.dao;

import by.petrovich.storage.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private final UserDao userDao = new UserDaoImpl();
    private final UserDaoImpl userDaoImpl = new UserDaoImpl();

    public DaoProvider() {
    }

	public static DaoProvider getInstance() {
		return instance;
	}

    public UserDaoImpl getUserDaoImpl() {
        return userDaoImpl;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
