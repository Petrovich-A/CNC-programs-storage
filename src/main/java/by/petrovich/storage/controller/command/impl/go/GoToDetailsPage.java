package by.petrovich.storage.controller.command.impl.go;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.Detail;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToDetailsPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		List<Detail> details = new ArrayList<>();
		try {
			details = cncProgramService.readDetail();
			session.setAttribute("details", details);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't read details", e);
		}
		return new Router(PathToPage.GO_TO_DETAILS_PAGE, RouterType.FORWARD);
	}

}
