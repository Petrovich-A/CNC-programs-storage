package by.petrovich.storage.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserDelete implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String DELETE_USER_SUCCESSFUL = "user delete is successful";
	private final String DELETE_USER_FAILD = "user delete is faild";

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		int loginPersonnelNumber;
		loginPersonnelNumber = Integer.parseInt(request.getParameter("loginPersonnelNumber"));
		HttpSession session = request.getSession(true);
		try {
			userService.delete(loginPersonnelNumber);
			session.setAttribute("message", DELETE_USER_SUCCESSFUL);
			logger.log(Level.INFO, "user with loginPersonnelNumber: {} is deleted", loginPersonnelNumber);
			return new Router(PathToPage.ADMIN, RouterType.REDIRECT);
		} catch (ServiceException e) {
			session.setAttribute("message", DELETE_USER_FAILD);
			logger.log(Level.INFO, "can't delete user with loginPersonnelNumber: {}", loginPersonnelNumber, e);
			return new Router(PathToPage.ADMIN, RouterType.FORWARD);
		}
	}

}
