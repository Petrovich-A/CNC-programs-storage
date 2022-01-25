package by.petrovich.storage.dao;

import by.petrovich.storage.dao.impl.CncProgramDaoImpl;
import by.petrovich.storage.dao.impl.UserDaoImpl;

public class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();
	private final UserDao userDao = new UserDaoImpl();
	private final CncProgramDao cncProgramDao = new CncProgramDaoImpl();

	public DaoProvider() {
	}

	public static DaoProvider getInstance() {
		return instance;
	}

	public CncProgramDao getCncProgramDao() {
		return cncProgramDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}
}
