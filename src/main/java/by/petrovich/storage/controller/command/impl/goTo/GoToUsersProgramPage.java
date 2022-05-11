package by.petrovich.storage.controller.command.impl.goTo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToUsersProgramPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();
	private final String ERROR = "";
	private final String ERROR_NO_USER = "No user in session";

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		List<CncProgram> cncPrograms = new ArrayList<>();
		User user = new User();
		user = (User) session.getAttribute("user");
		if (user == null) {
			request.setAttribute("error_message", ERROR_NO_USER);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		} else {
			try {
				cncPrograms = cncProgramService.receiveBatchByPersonnelNumber(user.getPersonnelNumber());
				request.setAttribute("cncPrograms", cncPrograms);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "Can't find user's CNC programs", e);
				request.setAttribute("error_message", ERROR);
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
		}
		return new Router(PathToPage.USERS_PROGRAM, RouterType.FORWARD);
	}

}
