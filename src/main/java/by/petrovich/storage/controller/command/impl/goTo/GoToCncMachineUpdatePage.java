package by.petrovich.storage.controller.command.impl.goTo;

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
			request.setAttribute("error_message", NO_CNC_MACHINE_ID);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		} else {
			session.setAttribute("cnc_machine_id", id);
			try {
				cncMachine = cncProgramService.readCncMachineById(id);
				request.setAttribute("cncMachine", cncMachine);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "CNC machine with id: {} can't be read", id, e);
				request.setAttribute("error_message", CNC_MACHINE_NOT_READ);
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
			return new Router(PathToPage.GO_TO_CNC_MACHINE_UPDATE_PAGE, RouterType.FORWARD);
		}
	}

}
