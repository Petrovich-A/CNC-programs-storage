package by.petrovich.storage.service;

import by.petrovich.storage.service.impl.CncProgramServiceImpl;
import by.petrovich.storage.service.impl.UserServiceImpl;

public class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();
	private final UserService userService = new UserServiceImpl();
	private final CncProgramService cncProgramService = new CncProgramServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public CncProgramService getCncProgramService() {
		return cncProgramService;
	}

}
