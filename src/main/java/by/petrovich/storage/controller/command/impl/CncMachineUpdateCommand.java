package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.ERROR_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.GO_TO_CNC_MACHINES_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.GO_TO_DETAIL_UPDATE_PAGE;
import static by.petrovich.storage.controller.command.RequestAttributeNames.ERROR_MESSAGE;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @author Petrovich A.V.
 *
 */
public class CncMachineUpdateCommand extends AbstractCommand {
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
			request.setAttribute(ERROR_MESSAGE, NO_CNC_MACHINE_ID);
			return new Router(ERROR_PAGE, RouterType.FORWARD);
		} else {
			try {
				cncProgramService.updateCncMachine(cncMachineFromUpdateForm, id);
				logger.log(Level.INFO, "CNC machine with id: {} is updated.", id);
				return createRouterWithAttribute(request, GO_TO_CNC_MACHINES_PAGE, "admin_cnc_machines_message",
						CNC_MACHINE_UPDATE_SUCCESSFUL);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "Can't update CNC machine with id: {}, CNC machine: {}", id,
						cncMachineFromUpdateForm.toString(), e);
				return createRouterWithAttribute(request, GO_TO_DETAIL_UPDATE_PAGE, "cnc_machine_update_message",
						CNC_MACHINE_UPDATE_FAILD);
			}
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private CncMachine buildCncMachine(HttpServletRequest request) {
		CncMachine cncMachine = new CncMachine();
		cncMachine.setModel(getParameterToCheck("model", request));
		cncMachine.setCodeEquipment(Integer.parseInt(getParameterToCheck("codeEquipment", request)));
		return cncMachine;
	}

}
