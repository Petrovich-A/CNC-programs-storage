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
import jakarta.servlet.http.HttpSession;

public class LogOutCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String LOG_OUT_FAILED = "Error: user logout failed.";

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = new User();
		user = (User) session.getAttribute("user");
		if (user == null) {
			logger.log(Level.ERROR, "HttpSession hasn't contain user");
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		} else {
			try {
				userService.logOut(user);
			} catch (ServiceException e) {
				session.setAttribute("error_message", LOG_OUT_FAILED);
				logger.log(Level.ERROR, "Can't log out", e);
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
		}
		session.removeAttribute("user");
		logger.log(Level.INFO, "user has been deleted from httpSession");
		return new Router(PathToPage.MAIN, RouterType.FORWARD);
	}

}
