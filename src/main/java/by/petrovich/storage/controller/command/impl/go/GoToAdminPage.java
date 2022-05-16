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
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAdminPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		int page = 1;
		int recordsPerPage = 7;
		int numberOfPages = 0;
		int numberOfRecords = 0;
		List<CncProgram> allCncPrograms = new ArrayList<>();
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		try {
			allCncPrograms = cncProgramService.receiveBatch((page - 1) * recordsPerPage, recordsPerPage);
			numberOfRecords = cncProgramService.receiveNumberOfRecords();
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "can't read allCncPrograms", e);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}
		numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
		request.setAttribute("allCncPrograms", allCncPrograms);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", page);
		return new Router(PathToPage.ADMIN, RouterType.FORWARD);
	}

}
