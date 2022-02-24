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
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		List<CncProgram> allCncPrograms = new ArrayList<>();
		try {
			allCncPrograms = cncProgramService.readAll();
		} catch (ServiceException e) {
			logger.log(Level.INFO, allCncPrograms.toString(), e);
		}
		session.setAttribute("allCncPrograms", allCncPrograms);

		int page = 1;
		int recordsPerPage = 5;
		List<CncProgram> cncPrograms = new ArrayList<>();
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			try {
				cncPrograms = cncProgramService.findAmountOfRows((page - 1) * recordsPerPage, recordsPerPage);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "can't read allCncPrograms", e);
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
		}
		return new Router(PathToPage.MAIN, RouterType.FORWARD);
	}

}
