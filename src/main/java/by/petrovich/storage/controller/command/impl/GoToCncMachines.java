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
import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToCncMachines implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		List<CncMachine> cncMachines = new ArrayList<>();
		try {
			cncMachines = cncProgramService.readCncMachine();
			request.setAttribute("cncMachines", cncMachines);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't read CNC machines", e);
		}
		return new Router(PathToPage.GO_TO_CNC_MACHINES, RouterType.FORWARD);
	}

}
