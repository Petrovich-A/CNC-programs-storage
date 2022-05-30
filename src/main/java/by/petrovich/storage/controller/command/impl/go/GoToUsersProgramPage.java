package by.petrovich.storage.controller.command.impl.go;

import static by.petrovich.storage.controller.command.RequestAttributeNames.CNC_PROGRAMS;
import static by.petrovich.storage.controller.command.RequestAttributeNames.ERROR_MESSAGE;

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
	private final String ERROR_NO_USER = "No user in session.";

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		List<CncProgram> cncPrograms = new ArrayList<>();
		User user = new User.Builder().build();
		user = (User) session.getAttribute("user");
		if (user == null) {
			request.setAttribute(ERROR_MESSAGE, ERROR_NO_USER);
			return new Router(PathToPage.ERROR_PAGE, RouterType.FORWARD);
		} else {
			try {
				cncPrograms = cncProgramService.receiveBatchByPersonnelNumber(user.getPersonnelNumber());
				request.setAttribute(CNC_PROGRAMS, cncPrograms);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "Can't find user's CNC programs", e);
				request.setAttribute(ERROR_MESSAGE, ERROR);
				return new Router(PathToPage.ERROR_PAGE, RouterType.FORWARD);
			}
		}
		return new Router(PathToPage.USERS_PROGRAM_PAGE, RouterType.FORWARD);
	}

}
