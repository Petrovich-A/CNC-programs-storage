package by.petrovich.storage.controller.command.impl;

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
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class Search implements Command {
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
		CncProgram cncProgram;
		try {
			cncProgram = cncProgramService.receiveCncProgram(searchData);
			boolean isSearchDataNumeric = isNumeric(searchData);
			if (cncProgram != null) {
				request.setAttribute("cncProgram", cncProgram);
				logger.log(Level.INFO, "Find CNC program by CNC program's name: {}", searchData);
				return new Router(PathToPage.CNC_PROGRAM_VIEW, RouterType.FORWARD);
			} else {
				List<CncProgram> cncProgramsByDetail = cncProgramService.receiveBatchByDetailName(searchData);
				if (cncProgramsByDetail != null && !cncProgramsByDetail.isEmpty()) {
					request.setAttribute("cncPrograms", cncProgramsByDetail);
					logger.log(Level.INFO, "Find some CNC programs by detail's name: {}", searchData);
					return new Router(PathToPage.GO_TO_DETAILS_CNC_PROGRAMS, RouterType.FORWARD);
				} else {
					if (isSearchDataNumeric) {
						User user = userService.read(Integer.valueOf(searchData));
						if (user != null) {
							request.setAttribute("userFromDao", user);
							logger.log(Level.INFO, "Find user by login personel number: {}", searchData);
							return new Router(PathToPage.USER_INFO, RouterType.FORWARD);
						}else {
							request.setAttribute("main_message", SEARCH_RESULTS_NOT_FOUND + searchData);
							return new Router(PathToPage.MAIN, RouterType.FORWARD);
						}
					} else {
						request.setAttribute("main_message", SEARCH_RESULTS_NOT_FOUND + searchData);
						return new Router(PathToPage.MAIN, RouterType.FORWARD);
					}
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't execute search query.", e);
			request.setAttribute("error_message", SEARCHING_IS_FAILED);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}

	}

	private boolean isNumeric(String stringData) {
		return stringData != null && stringData.matches(REG_EX_DIGIT);
	}

}
