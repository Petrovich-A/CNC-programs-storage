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

public class CncProgramUpdate implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String CNC_PROGRAM_UPDATE_SUCCESSFUL = "CNC program update successful";
	private static final String CNC_PROGRAM_UPDATE_ERROR_SESSION = "Session doesn't contain CNC program id for updating.";
	private static final String CNC_PROGRAM_UPDATE_ERROR = "Error in CNC program updating.";
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		CncProgram cncProgram = buildCncProgram(request);
		int id = 0;
		if (session.getAttribute("id") != null) {
			id = (Integer) session.getAttribute("id");
		} else {
			logger.log(Level.ERROR, "Session doesn't contain CNC program id.");
			request.setAttribute("error_message", CNC_PROGRAM_UPDATE_ERROR_SESSION);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}
		try {
			cncProgramService.update(cncProgram, id);
			request.setAttribute("cnc_program_update_message", CNC_PROGRAM_UPDATE_SUCCESSFUL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't update CNC program with id {}", id, e);
			request.setAttribute("cnc_program_update_message", CNC_PROGRAM_UPDATE_ERROR);
			return new Router(PathToPage.CNC_PROGRAM_UPDATE, RouterType.FORWARD);
		}
		return new Router(PathToPage.AD, RouterType.FORWARD);
	}

	private CncProgram buildCncProgram(HttpServletRequest request) {
		CncProgram cncProgram = new CncProgram();
		cncProgram.setNumber(getParameterToCheck("number", request));
		cncProgram.setOperationNumber(Integer.parseInt(getParameterToCheck("operationNumber", request)));
		cncProgram.setComment(getParameterToCheck("comment", request));
		if (getParameterToCheck("isActive", request) == "active") {
			cncProgram.setActive(true);
		} else {
			cncProgram.setActive(false);
		}
		return cncProgram;
	}

	private String getParameterToCheck(String name, HttpServletRequest request) {
		final String parameter = request.getParameter(name);
		if (parameter == null) {
			logger.log(Level.ERROR, "request have no parameter with name: {}" + name);
			throw new IllegalArgumentException("request have no parameter with name: {}" + name);
		}
		return parameter;
	}

}
