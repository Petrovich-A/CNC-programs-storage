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
	private final String AUTHORIZATION_SUCCESSFUL = "Authorization is completed successfully!";
	private final String AUTHORIZATION_FAILED = "Error: user authorization failed. Please reapeat authorization.";
	private final String USER_IS_NOT_EXIST = "User isn't exist with such login or password.";

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		User userFromAuthorizationForm = new User();
		User user = new User();
		boolean isUserExist = false;
		boolean isUserLoginAndIsPasswordMatch = false;
		userFromAuthorizationForm
				.setLoginPersonnelNumber(Integer.parseInt(getParameterToCheck("loginPersonnelNumber", request)));
		userFromAuthorizationForm.setPassword(getParameterToCheck("password", request));
		try {
			isUserExist = userService.isExist(userFromAuthorizationForm);
		} catch (ServiceException e1) {
			logger.log(Level.ERROR, "Can't check is user with LoginPersonnelNumber: {} exist in BD",
					userFromAuthorizationForm.getLoginPersonnelNumber(), e1);
			request.setAttribute("authorization_message", USER_IS_NOT_EXIST);
			return new Router(PathToPage.AUTHORIZATION, RouterType.FORWARD);
		}
		try {
			isUserLoginAndIsPasswordMatch = userService.isUsersLoginAndIsPasswordMatch(userFromAuthorizationForm);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (isUserExist && isUserLoginAndIsPasswordMatch) {
			try {
				user = userService.authorizate(userFromAuthorizationForm);
				session.setAttribute("user", user);
				request.setAttribute("main_message", AUTHORIZATION_SUCCESSFUL);
				return new Router(PathToPage.MAIN, RouterType.FORWARD);
			} catch (ServiceException e) {
				request.setAttribute("authorization_message", AUTHORIZATION_FAILED);
				logger.log(Level.ERROR, "authorization failed", e.getLocalizedMessage(), e);
				return new Router(PathToPage.AUTHORIZATION, RouterType.FORWARD);
			}
		} else {
			request.setAttribute("authorization_message", USER_IS_NOT_EXIST);
			logger.log(Level.INFO, "authorization failed");
			return new Router(PathToPage.AUTHORIZATION, RouterType.FORWARD);
		}
	}

	private String getParameterToCheck(String parameter, HttpServletRequest request) {
		final String parameterFromRequest = request.getParameter(parameter);
		if (parameterFromRequest == null || parameterFromRequest.equals("")) {
			logger.log(Level.ERROR, "Request contains parameter eaqual null: {}", parameterFromRequest);
			throw new IllegalArgumentException("Request contains parameter eaqual null: {}" + parameterFromRequest);
		}
		return parameterFromRequest;
	}
}
