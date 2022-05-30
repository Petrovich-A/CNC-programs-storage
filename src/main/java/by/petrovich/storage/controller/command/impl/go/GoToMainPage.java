package by.petrovich.storage.controller.command.impl.go;

import static by.petrovich.storage.controller.command.PathToPage.ERROR_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.MAIN_PAGE;
import static by.petrovich.storage.controller.command.SessionAttributeNames.PATH_TO_PAGE;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute(PATH_TO_PAGE, MAIN_PAGE);
		List<CncProgram> allCncPrograms = new ArrayList<>();
		try {
			allCncPrograms = cncProgramService.receiveBatchByDate();
			session.setAttribute("allCncPrograms", allCncPrograms);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't read list of all Cnc Programs.", e);
			return new Router(ERROR_PAGE, RouterType.FORWARD);
		}
		return new Router(MAIN_PAGE, RouterType.FORWARD);
	}

}
