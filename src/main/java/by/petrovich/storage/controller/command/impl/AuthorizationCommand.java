package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.AUTHORIZATION_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.ERROR_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.MAIN_PAGE;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @author Petrovich A.V.
 *
 */
public class AuthorizationCommand extends AbstractCommand {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String AUTHORIZATION_SUCCESSFUL = "Authorization is completed successfully!";
	private final String AUTHORIZATION_FAILED = "Error: user authorization failed.";
	private final String WRONG_INPUT_DATA = "Something went wrong. Please try to reapeat authorization again.";

	@Override
	public Router execute(HttpServletRequest request) {
		int login = Integer.parseInt(getParameterToCheck("personnelNumber", request));
		String password = getParameterToCheck("password", request);
		try {
			boolean isUserExist = userService.isUserExist(login);
			boolean isLoginAndPasswordMatchWithDateBaseData = false;
			boolean isLoginAndPasswordValid = false;
			if (isUserExist) {
				isLoginAndPasswordMatchWithDateBaseData = userService.isLoginAndPasswordMatchWithDateBaseData(login,
						password);
				if (isLoginAndPasswordMatchWithDateBaseData) {
					isLoginAndPasswordValid = userService.isLoginAndPasswordValid(login, password);
				}
			}
			if (isUserExist && isLoginAndPasswordMatchWithDateBaseData && isLoginAndPasswordValid) {
				return authorizate(request, login, password);
			} else {
				logger.log(Level.INFO, "Wrong input data");
				return createRouterWithAttribute(request, AUTHORIZATION_PAGE, "authorization_message", WRONG_INPUT_DATA);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User authorization with personnel number: {} is faild", login, e);
			return createRouterWithAttribute(request, ERROR_PAGE, "error_message", AUTHORIZATION_FAILED);
		}
	}

	/**
	 * @param request
	 * @param login
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	private Router authorizate(HttpServletRequest request, int login, String password) throws ServiceException {
		HttpSession session = request.getSession(true);
		User user = userService.authorizateUser(login, password).get();
		session.setAttribute("user", user);
		return createRouterWithAttribute(request, MAIN_PAGE, "main_message", AUTHORIZATION_SUCCESSFUL);
	}

}
