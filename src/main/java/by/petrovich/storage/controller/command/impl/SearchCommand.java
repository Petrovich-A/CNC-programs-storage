package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.CNC_PROGRAM_VIEW;
import static by.petrovich.storage.controller.command.PathToPage.ERROR;
import static by.petrovich.storage.controller.command.PathToPage.GO_TO_DETAILS_CNC_PROGRAMS;
import static by.petrovich.storage.controller.command.PathToPage.MAIN;
import static by.petrovich.storage.controller.command.PathToPage.USER_INFO;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class SearchCommand extends AbstractCommand {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();
	private final UserService userService = serviceProvider.getUserService();
	private final String SEARCHING_IS_FAILED = "Error: can't execute searching.";
	private final String SEARCH_RESULTS_NOT_FOUND = "No search results for your query: ";
	private final String REG_EX_DIGIT = "[\\d.]+";

	@Override
	public Router execute(HttpServletRequest request) {
		String searchData = request.getParameter("searchInput");
		try {
			CncProgram cncProgram = cncProgramService.readCncProgramByName(searchData);
			if (cncProgram != null) {
				logger.log(Level.INFO, "Find CNC program by CNC program's name: {}", searchData);
				return createRouterWithAttribute(request, CNC_PROGRAM_VIEW, "cncProgram", cncProgram);
			}
			List<CncProgram> cncProgramsByDetail = cncProgramService.receiveBatchByDetailName(searchData);
			if (!cncProgramsByDetail.isEmpty()) {
				logger.log(Level.INFO, "Find some CNC programs by detail's name: {}", searchData);
				return createRouterWithAttribute(request, GO_TO_DETAILS_CNC_PROGRAMS, "cncPrograms",
						cncProgramsByDetail);
			}
			Optional<User> userOptional = Optional.empty();
			if (isNumeric(searchData)) {
				userOptional = userService.readUserByPersonnelNumber(Integer.valueOf(searchData));
				if (userOptional.isPresent()) {
					User user = userOptional.get();
					logger.log(Level.INFO, "Find user by login personel number: {}", searchData);
					return createRouterWithAttribute(request, USER_INFO, "userFromDao", user);
				}
			}
			return createRouterWithAttribute(request, MAIN, "main_message", SEARCH_RESULTS_NOT_FOUND + searchData);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't execute search query.", e);
			request.setAttribute("error_message", SEARCHING_IS_FAILED);
			return new Router(ERROR, RouterType.FORWARD);
		}
	}

	private boolean isNumeric(String stringData) {
		return stringData != null && stringData.matches(REG_EX_DIGIT);
	}

}
