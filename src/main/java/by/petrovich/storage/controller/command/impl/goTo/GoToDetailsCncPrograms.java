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
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToDetailsCncPrograms implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();
	private final String NO_CNC_PROGRAMS = "Error in obtaining CNC programs by detail name.";

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		List<CncProgram> cncProgramsByDetail = new ArrayList<>();
		String detailName = null;
		if (request.getParameter("detail_name") != null)
			request.setAttribute("error_message", NO_CNC_PROGRAMS);
			detailName = request.getParameter("detail_name");
		try {
			cncProgramsByDetail = cncProgramService.recieveBatchByDetailName(detailName);
			session.setAttribute("cncProgramsByDetail", cncProgramsByDetail);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't find CNC programs by detail name. Detail name: {}.", detailName, e);
			request.setAttribute("error_message", NO_CNC_PROGRAMS);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}
		return new Router(PathToPage.GO_TO_DETAILS_CNC_PROGRAMS, RouterType.FORWARD);
	}

}
