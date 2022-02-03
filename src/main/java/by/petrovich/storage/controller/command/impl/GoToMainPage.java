package by.petrovich.storage.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.CncProgramService;
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
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		List<User> allUsers = new ArrayList<>();
		try {
			allUsers = userService.readAll();
		} catch (ServiceException e) {
			logger.log(Level.DEBUG, allUsers.toString(), e);
		}
		session.setAttribute("allUsers", allUsers);

		int page = 1;
		int recordsPerPage = 5;
		List<CncProgram> cncPrograms = new ArrayList<>();
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			try {
				cncPrograms = cncProgramService.findAmountOfRows((page - 1) * recordsPerPage, recordsPerPage);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.MAIN);
		requestDispatcher.forward(request, response);
	}

}
