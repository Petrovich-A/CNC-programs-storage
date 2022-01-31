package by.petrovich.storage.controller.command.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToUserInfo implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = new User();
		int loginPersonnelNumber = Integer.parseInt(request.getParameter("loginPersonnelNumber"));
		try {
			user = userService.read(loginPersonnelNumber);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "user with loginPersonnelNumber: {} can't be read", loginPersonnelNumber);
			e.printStackTrace();
		}
		session.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.USER_INFO);
		requestDispatcher.forward(request, response);
	}

}
