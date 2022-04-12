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

public class SearchByCncProgramName implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();
	private final String SEARCH_BY_CNC_PROGRAM_NAME_FAILED = "Error: can't find CNC program.";
	private final String NO_CNC_PROGRAM_FOUND = "No such CNC program in BD as: ";

	@Override
	public Router execute(HttpServletRequest request) {
		String searchInput = request.getParameter("searchInput");
		CncProgram cncProgram = new CncProgram();
		try {
			cncProgram = cncProgramService.searchCncProgram(searchInput);
			if (cncProgram != null) {
				request.setAttribute("cncProgram", cncProgram);
				logger.log(Level.INFO, "Find CNC program with name: {}", searchInput);
			} else {
				request.setAttribute("search_cnc_program_message", NO_CNC_PROGRAM_FOUND + searchInput);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't find CNC program", e);
			request.setAttribute("error_message", SEARCH_BY_CNC_PROGRAM_NAME_FAILED);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}
		return new Router(PathToPage.CNC_PROGRAM_VIEW, RouterType.FORWARD);
	}

}
