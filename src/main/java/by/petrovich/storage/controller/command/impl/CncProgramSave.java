package by.petrovich.storage.controller.command.impl;

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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;

public class CncProgramSave implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();
	private final String CNC_PROGRAM_SAVE_SUCCESSFUL = "CNC program saving is successful";
	private final String CNC_PROGRAM_SAVE__FAILD = "CNC program saving  is faild";

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		CncProgram cncProgramFromMainForm = buildCncProgram(request);
		HttpSession session = request.getSession(true);
		User user = new User();
		user = (User) session.getAttribute("user");
		if (user != null) {
			try {
				logger.log(Level.INFO, "Cnc program from main form is received", cncProgramFromMainForm.toString());
				cncProgramService.create(cncProgramFromMainForm, user.getLoginPersonnelNumber());
				session.setAttribute("mesage", CNC_PROGRAM_SAVE_SUCCESSFUL);
				return new Router(PathToPage.MAIN, RouterType.FORWARD);
			} catch (ServiceException e) {
				session.setAttribute("mesage", CNC_PROGRAM_SAVE__FAILD);
				logger.log(Level.ERROR, e.getLocalizedMessage());
				return new Router(PathToPage.MAIN, RouterType.FORWARD);
			}
		}
		return new Router(PathToPage.MAIN, RouterType.FORWARD);
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
