package by.petrovich.storage.controller.command.impl;

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
import jakarta.servlet.http.HttpSession;

public class CncMachineUpdate implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String NO_CNC_MACHINE_ID = "No CNC machine id.";
	private static final String CNC_MACHINE_UPDATE_SUCCESSFUL = "CNC machine updating is successful.";
	private static final String CNC_MACHINE_UPDATE_FAILD = "CNC machine updating is faild.";
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		CncMachine cncMachineFromUpdateForm = buildCncMachine(request);
		int id = (Integer) session.getAttribute("cnc_machine_id");
		if (id == 0) {
			request.setAttribute("error_message", NO_CNC_MACHINE_ID);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		} else {
			try {
				cncProgramService.updateCncMachine(cncMachineFromUpdateForm, id);
				request.setAttribute("admin_cnc_machines_message", CNC_MACHINE_UPDATE_SUCCESSFUL);
				logger.log(Level.INFO, "CNC machine with id: {} is updated.", id);
				return new Router(PathToPage.GO_TO_CNC_MACHINES, RouterType.FORWARD);
			} catch (ServiceException e) {
				request.setAttribute("cnc_machine_update_message", CNC_MACHINE_UPDATE_FAILD);
				logger.log(Level.ERROR, "Can't update CNC machine with id: {}, CNC machine: {}", id,
						cncMachineFromUpdateForm.toString(), e);
				return new Router(PathToPage.GO_TO_DETAIL_UPDATE_PAGE, RouterType.FORWARD);
			}
		}
	}

	private CncMachine buildCncMachine(HttpServletRequest request) {
		CncMachine cncMachine = new CncMachine();
		cncMachine.setModel(getParameterToCheck("model", request));
		cncMachine.setCodeEquipment(Integer.parseInt(getParameterToCheck("codeEquipment", request)));
		return cncMachine;
	}

	private String getParameterToCheck(String name, HttpServletRequest request) {
		final String parameter = request.getParameter(name);
		if (parameter == null) {
			throw new IllegalArgumentException("Request doesn't have parameter with name: {}" + name);
		}
		return parameter;
	}

}
