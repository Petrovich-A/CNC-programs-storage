package by.petrovich.storage.controller.command.impl;

import java.io.IOException;
import java.sql.Timestamp;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CncProgramSave implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CncProgram cncProgramFromMainForm = buildProgram(request);
		try {
			logger.log(Level.DEBUG, "Cnc program from main form is received", cncProgramFromMainForm.toString());
			cncProgramService.create(cncProgramFromMainForm);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.MAIN);
			requestDispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.log(Level.ERROR, e.getLocalizedMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private CncProgram buildProgram(HttpServletRequest request) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		CncProgram cncProgram = new CncProgram();
		cncProgram.setName(getParameterToCheck("number", request));
		cncProgram.setOperationNumber(Integer.parseInt(getParameterToCheck("operationNumber", request)));
		cncProgram.setFileExtension(getParameterToCheck("fileExtension", request));
		cncProgram.setProgramText(getParameterToCheck("programText", request));
		cncProgram.setComment(getParameterToCheck("comment", request));
		cncProgram.setActive(true);
		cncProgram.setDate(timestamp);
		return cncProgram;
	}

	private String getParameterToCheck(String name, HttpServletRequest request) {
		final String parameter = request.getParameter(name);
		if (parameter == null) {
			throw new IllegalArgumentException("request have no parameter with name: " + name);
		}
		return parameter;
	}

}