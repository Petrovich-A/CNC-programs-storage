package by.petrovich.storage.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogOut implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		User user = new User();
		user = (User) session.getAttribute("user");
		if (user == null) {
			logger.log(Level.ERROR, "HttpSession hasn't contain user");
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		} else {
			user.setUserRole(UserRole.GUEST);
			try {
				userService.update(user, user.getLoginPersonnelNumber());
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "can't change user role (update)", e);
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
		}
		session.removeAttribute("user");
		logger.log(Level.INFO, "user has been deleted from httpSession");
		return new Router(PathToPage.MAIN, RouterType.FORWARD);
	}

}
