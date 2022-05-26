package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.AUTHORIZATION_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.ERROR_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.MAIN_PAGE;

import java.sql.Timestamp;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @author Petrovich A.V.
 *
 */
public class CncProgramSaveCommand extends AbstractCommand {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();
	private final String CNC_PROGRAM_SAVE_SUCCESSFUL = "CNC program is saved successful";
	private final String CNC_PROGRAM_SAVE_ERROR = "We apologize for the inconvenience. "
			+ "However we have some problems with CNC program saving. "
			+ "Plese try again or contact to the administrator.";

	@Override
	public Router execute(HttpServletRequest request) {
		CncProgram cncProgramFromMainForm = buildCncProgram(request);
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			logger.log(Level.ERROR, "have no user data in session");
			return new Router(AUTHORIZATION_PAGE, RouterType.FORWARD);
		} else {
			try {
				logger.log(Level.INFO, "Cnc program from main form is received", cncProgramFromMainForm.toString());
				cncProgramFromMainForm.setPersonnelNumber(user.getPersonnelNumber());
				cncProgramService.createCncProgram(cncProgramFromMainForm);
				session.setAttribute("main_message", CNC_PROGRAM_SAVE_SUCCESSFUL);
				return new Router(MAIN_PAGE, RouterType.FORWARD);
			} catch (ServiceException e) {
				session.setAttribute("error_message", CNC_PROGRAM_SAVE_ERROR);
				logger.log(Level.ERROR, e.getLocalizedMessage());
				return new Router(ERROR_PAGE, RouterType.FORWARD);
			}
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
		cncProgram.setProgramText(getParameterToCheck("programText", request));
		cncProgram.setCreationDate(new Timestamp(System.currentTimeMillis()));
		if (request.getAttribute("comment") != null) {
			cncProgram.setComment((String) request.getAttribute("comment"));
		}
		cncProgram.setDetail(buildDetail(request));
		cncProgram.setCncMachine(buildCncMachine(request));
		return cncProgram;
	}

	/**
	 * @param request
	 * @return
	 */
	private Detail buildDetail(HttpServletRequest request) {
		Detail detail = new Detail();
		detail.setName(getParameterToCheck("detail", request));
		return detail;
	}

	/**
	 * @param request
	 * @return
	 */
	private CncMachine buildCncMachine(HttpServletRequest request) {
		CncMachine cncMachine = new CncMachine();
		cncMachine.setModel(getParameterToCheck("cncMachine", request));
		cncMachine.setCodeEquipment(Integer.parseInt(getParameterToCheck("codeEquipment", request)));
		return cncMachine;
	}

}
