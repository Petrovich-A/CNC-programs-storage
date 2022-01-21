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

public class GoToMainPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		User user = new User();
		try {
			user = userService.read(12341);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			logger.log(Level.DEBUG, user.toString(), e);
			e.printStackTrace();
		}
		session.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.MAIN);
		requestDispatcher.forward(request, response);
	}

}
