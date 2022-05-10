package by.petrovich.storage.controller.command.impl.goTo;

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

public class GoToDetailUpdatePage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String NO_DETAIL_ID = "No detail id.";
	private static final String DETAIL_NOT_READ = "Detail not read.";
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Detail detail = new Detail();
		int id = Integer.parseInt(request.getParameter("detail_id"));
		if (id == 0) {
			request.setAttribute("error_message", NO_DETAIL_ID);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		} else {
			session.setAttribute("detail_id", id);
			try {
				detail = cncProgramService.readDetailById(id);
				request.setAttribute("detail", detail);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "Detail with id: {} can't be read", id, e);
				request.setAttribute("error_message", DETAIL_NOT_READ);
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
			return new Router(PathToPage.GO_TO_DETAIL_UPDATE_PAGE, RouterType.FORWARD);
		}
	}

}
