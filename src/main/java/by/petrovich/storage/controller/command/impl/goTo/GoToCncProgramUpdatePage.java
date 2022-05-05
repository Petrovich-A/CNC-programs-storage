package by.petrovich.storage.controller.command.impl.goTo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToCncProgramUpdatePage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		CncProgram cncProgram = new CncProgram();
		int id = Integer.parseInt(request.getParameter("id"));
		session.setAttribute("id", id);
		try {
			cncProgram = cncProgramService.receiveCncProgramById(id);
			session.setAttribute("cncProgram", cncProgram);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "CNC program with id: {} can't be read", id, e);
		}
		return new Router(PathToPage.CNC_PROGRAM_UPDATE, RouterType.FORWARD);
	}

}
