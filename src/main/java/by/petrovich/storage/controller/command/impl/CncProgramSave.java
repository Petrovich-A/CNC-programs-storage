package by.petrovich.storage.controller.command.impl;

import java.sql.Timestamp;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
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

public class CncProgramSave implements Command {
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
		User user = new User();
		user = (User) session.getAttribute("user");
		if (user == null) {
			logger.log(Level.ERROR, "have no user data in session");
			return new Router(PathToPage.AUTHORIZATION, RouterType.FORWARD);
		} else {
			try {
				logger.log(Level.INFO, "Cnc program from main form is received", cncProgramFromMainForm.toString());
				cncProgramFromMainForm.setLoginPersonnelNumber(user.getLoginPersonnelNumber());
				cncProgramService.createCncProgram(cncProgramFromMainForm);
				session.setAttribute("main_message", CNC_PROGRAM_SAVE_SUCCESSFUL);
				return new Router(PathToPage.MAIN, RouterType.FORWARD);
			} catch (ServiceException e) {
				session.setAttribute("error_message", CNC_PROGRAM_SAVE_ERROR);
				logger.log(Level.ERROR, e.getLocalizedMessage());
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
		}
	}

	private CncProgram buildCncProgram(HttpServletRequest request) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		CncProgram cncProgram = new CncProgram();
		cncProgram.setNumber(getParameterToCheck("number", request));
		cncProgram.setOperationNumber(Integer.parseInt(getParameterToCheck("operationNumber", request)));
		cncProgram.setProgramText(getParameterToCheck("programText", request));
		cncProgram.setCreationDate(timestamp);
		cncProgram.setComment(getParameterToCheck("comment", request));
		cncProgram.setDetail(buildDetail(request));
		cncProgram.setCncMachine(buildCncMachine(request));
		return cncProgram;
	}

	private Detail buildDetail(HttpServletRequest request) {
		Detail detail = new Detail();
		detail.setName(getParameterToCheck("detail", request));
		return detail;
	}

	private CncMachine buildCncMachine(HttpServletRequest request) {
		CncMachine cncMachine = new CncMachine();
		cncMachine.setModel(getParameterToCheck("cncMachine", request));
		cncMachine.setCodeEquipment(Integer.parseInt(getParameterToCheck("codeEquipment", request)));
		return cncMachine;
	}

	private String getParameterToCheck(String name, HttpServletRequest request) {
		final String parameter = request.getParameter(name);
		if (parameter == null) {
			throw new IllegalArgumentException("request have no parameter with name: {}" + name);
		}
		return parameter;
	}

}
