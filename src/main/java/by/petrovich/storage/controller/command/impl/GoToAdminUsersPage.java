package by.petrovich.storage.controller.command.impl;

import java.util.ArrayList;
import java.util.List;

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

public class GoToAdminUsersPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		List<User> allUsers = new ArrayList<>();
		try {
			allUsers = userService.readAll();
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "can't read allUsers", allUsers, e);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}
		session.setAttribute("allUsers", allUsers);
		return new Router(PathToPage.ADMIN_USERS, RouterType.FORWARD);
	}

}
