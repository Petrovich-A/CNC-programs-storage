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

public class GoToUpdateUserPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		User user = new User();
		int loginPersonnelNumber = Integer.parseInt(request.getParameter("loginPersonnelNumber"));
		try {
			user = userService.read(loginPersonnelNumber);
			session.setAttribute("user", user);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "user with loginPersonnelNumber: {} can't be read", loginPersonnelNumber, e);
		}
		return new Router(PathToPage.USER_UPDATE, RouterType.FORWARD);
	}

}
