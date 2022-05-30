package by.petrovich.storage.controller.command.impl.go;

import static by.petrovich.storage.controller.command.RequestAttributeNames.CNC_MACHINE;
import static by.petrovich.storage.controller.command.RequestAttributeNames.ERROR_MESSAGE;
import static by.petrovich.storage.controller.command.SessionAttributeNames.CNC_MACHINE_ID;

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

public class GoToCncMachineUpdatePage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String NO_CNC_MACHINE_ID = "No CNC machine id.";
	private static final String CNC_MACHINE_NOT_READ = "CNC machine isn't read.";
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		CncMachine cncMachine = new CncMachine();
		int id = Integer.parseInt(request.getParameter("cnc_machine_id"));
		if (id == 0) {
			request.setAttribute(ERROR_MESSAGE, NO_CNC_MACHINE_ID);
			return new Router(PathToPage.ERROR_PAGE, RouterType.FORWARD);
		} else {
			session.setAttribute(CNC_MACHINE_ID, id);
			try {
				cncMachine = cncProgramService.readCncMachine(id);
				request.setAttribute(CNC_MACHINE, cncMachine);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "CNC machine with id: {} can't be read", id, e);
				request.setAttribute(ERROR_MESSAGE, CNC_MACHINE_NOT_READ);
				return new Router(PathToPage.ERROR_PAGE, RouterType.FORWARD);
			}
			return new Router(PathToPage.GO_TO_CNC_MACHINE_UPDATE_PAGE, RouterType.FORWARD);
		}
	}

}
