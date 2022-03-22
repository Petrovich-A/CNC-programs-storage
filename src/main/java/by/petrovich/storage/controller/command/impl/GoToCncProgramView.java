package by.petrovich.storage.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToCncProgramView implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();
	private final static String CANT_READ_CNC_PROGRAM = "Can't read CNC program by id: ";

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		int id = 0;
		CncProgram cncProgram = new CncProgram();
		HttpSession session = request.getSession(true);
		if (request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));
		try {
			cncProgram = cncProgramService.read(id);
			session.setAttribute("cncProgram", cncProgram);
		} catch (ServiceException e) {
			session.setAttribute("error_message", CANT_READ_CNC_PROGRAM + id);
			logger.log(Level.ERROR, "Can't read CNC program by id {}", id, e);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}
		return new Router(PathToPage.CNC_PROGRAM_VIEW, RouterType.FORWARD);
	}

}
