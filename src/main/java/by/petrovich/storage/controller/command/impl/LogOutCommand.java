package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.ERROR;
import static by.petrovich.storage.controller.command.PathToPage.MAIN;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogOutCommand extends AbstractCommand {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String LOG_OUT_FAILED = "Error: user logout failed.";
	private final String NO_USER_IN_SESSION = "Error: httpSession don't contain user.";

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			logger.log(Level.ERROR, "HttpSession don't contain user");
			return createRouterWithAttribute(request, ERROR, "error_message", NO_USER_IN_SESSION);
		} else {
			try {
				userService.logOut(user);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "Can't log out", e);
				return createRouterWithAttribute(request, ERROR, "error_message", LOG_OUT_FAILED);
			}
		}
		session.removeAttribute("user");
		logger.log(Level.INFO, "User has been deleted from httpSession.");
		return new Router(MAIN, RouterType.FORWARD);
	}

}
