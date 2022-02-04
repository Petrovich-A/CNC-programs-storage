package by.petrovich.storage.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthorizationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		String loginPersonnelNumber = getParameterToCheck("loginPersonnelNumber", request);
		String password = getParameterToCheck("password", request);
		HttpSession session = request.getSession(true);
		User userFromAuthorizForm = new User(Integer.parseInt(loginPersonnelNumber), password);
		try {
			User userFromDao = userService.authorizate(userFromAuthorizForm);
			session.setAttribute("userFromDao", userFromDao);
			return new Router(PathToPage.MAIN, RouterType.FORWARD);
		} catch (ServiceException e) {
			return new Router(PathToPage.AUTHORIZATION, RouterType.FORWARD);
		}
	}

	private String getParameterToCheck(String parameter, HttpServletRequest request) {
		final String parameterFromRequest = request.getParameter(parameter);
		if (parameterFromRequest == null || parameterFromRequest.equals("")) {
			logger.log(Level.ERROR, "request contains parameter eaqual null: ", parameterFromRequest);
			throw new IllegalArgumentException("request contains parameter eaqual null: " + parameterFromRequest);
		}
		return parameterFromRequest;
	}
}
