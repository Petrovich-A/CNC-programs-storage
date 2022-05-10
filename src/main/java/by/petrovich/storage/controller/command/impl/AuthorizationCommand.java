package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.AUTHORIZATION;
import static by.petrovich.storage.controller.command.PathToPage.ERROR;
import static by.petrovich.storage.controller.command.PathToPage.MAIN;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthorizationCommand extends AbstractCommand {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String AUTHORIZATION_SUCCESSFUL = "Authorization is completed successfully!";
	private final String AUTHORIZATION_FAILED = "Error: user authorization failed.";
	private final String WRONG = "Something went wrong. Please try to reapeat authorization again.";

	@Override
	public Router execute(HttpServletRequest request) {
		int login = Integer.parseInt(getParameterToCheck("loginPersonnelNumber", request));
		String password = getParameterToCheck("password", request);
		try {
			boolean isUserExist = userService.isUserExist(login);
			boolean isUserLoginAndPasswordMatch = false;
			if (isUserExist) {
				isUserLoginAndPasswordMatch = userService.isLoginAndPasswordMatch(login, password);
			}
			if (isUserExist && isUserLoginAndPasswordMatch) {
				return authorize(request, login, password);
			} else {
				logger.log(Level.INFO, "Authorization failed");
				return createRouterWithAttribute(request, AUTHORIZATION, "authorization_message", WRONG);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User authorization with personnel number: {} is faild", login, e);
			return createRouterWithAttribute(request, ERROR, "error_message", AUTHORIZATION_FAILED);
		}
	}

	private Router authorize(HttpServletRequest request, int login, String password) throws ServiceException {
		HttpSession session = request.getSession(true);
		User user = userService.authorizateUser(login, password).get();
		session.setAttribute("user", user);
		return createRouterWithAttribute(request, MAIN, "main_message", AUTHORIZATION_SUCCESSFUL);
	}

}
