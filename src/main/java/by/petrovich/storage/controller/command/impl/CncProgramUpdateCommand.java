package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.CNC_PROGRAM_UPDATE_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.ERROR_PAGE;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @author Petrovich A.V.
 *
 */
public class CncProgramUpdateCommand extends AbstractCommand {
	private static final Logger logger = LogManager.getLogger();
	private static final String CNC_PROGRAM_UPDATE_SUCCESSFUL = "CNC program update successful";
	private static final String CNC_PROGRAM_UPDATE_ERROR_SESSION = "Session doesn't contain CNC program id for updating.";
	private static final String CNC_PROGRAM_UPDATE_ERROR = "Error in CNC program updating.";
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		CncProgram cncProgram = buildCncProgram(request);
		int id = 0;
		if (session.getAttribute("id") != null) {
			id = (Integer) session.getAttribute("id");
		} else {
			logger.log(Level.ERROR, "Session doesn't contain CNC program id.");
			return createRouterWithAttribute(request, ERROR_PAGE, "error_message", CNC_PROGRAM_UPDATE_ERROR_SESSION);
		}
		try {
			cncProgramService.updateCncProgram(cncProgram, id);
			return createRouterWithAttribute(request, CNC_PROGRAM_UPDATE_PAGE, "cnc_program_update_message",
					CNC_PROGRAM_UPDATE_SUCCESSFUL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't update CNC program with id {}", id, e);
			return createRouterWithAttribute(request, CNC_PROGRAM_UPDATE_PAGE, "cnc_program_update_message",
					CNC_PROGRAM_UPDATE_ERROR);
		}
	}

	/**
	 * @param request
	 * @return
	 */
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

}
