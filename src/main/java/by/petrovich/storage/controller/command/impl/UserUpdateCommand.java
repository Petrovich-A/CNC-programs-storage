package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.ADMIN_USERS_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.USER_UPDATE_PAGE;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @author Petrovich A.V.
 *
 */
public class UserUpdateCommand extends AbstractCommand {
	private static final Logger logger = LogManager.getLogger();
	private static final String UPDATE_USER_SUCCESSFUL = "User updating is successful.";
	private static final String UPDATE_USER_FAILD = "User updating is faild.";
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User userFromUpdateForm = buildUser(request);
		User userFromSession = (User) getParametrToCheck("user", session);
		int personnelNumber = userFromSession.getPersonnelNumber();
		try {
			userService.update(userFromUpdateForm, personnelNumber);
			logger.log(Level.INFO, "User with personnel number: {} is updated.", personnelNumber);
			return createRouterWithAttribute(request, ADMIN_USERS_PAGE, "admin_users_message", UPDATE_USER_SUCCESSFUL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't update user with personnelNumber: {}, user: {}.", personnelNumber,
					userFromUpdateForm.toString(), e);
			return createRouterWithAttribute(request, USER_UPDATE_PAGE, "error_message", UPDATE_USER_FAILD);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private User buildUser(HttpServletRequest request) {
		User user = new User.Builder().withEmployeeName(getParameterToCheck("employeeName", request))
				.withEmployeeSurname(getParameterToCheck("employeeSurname", request))
				.withEmployeePatronymic(getParameterToCheck("employeePatronymic", request))
				.withEmployeePosition(EmployeePosition.fromString(getParameterToCheck("employeePosition", request)))
				.withUserRole(UserRole.fromString(getParameterToCheck("userRole", request)))
				.withEmail(getParameterToCheck("email", request)).build();
		logger.log(Level.INFO, "User is built. User: {}", user.toString());
		return user;
	}

	/**
	 * @param parameter
	 * @param session
	 * @return
	 */
	private Object getParametrToCheck(String parameter, HttpSession session) {
		Object object = session.getAttribute(parameter);
		if (object.equals(null)) {
			logger.log(Level.ERROR, "Session contains null: {}.", object);
			throw new IllegalArgumentException("Session contains parameter eaqual null: {}." + object);
		}
		return object;
	}

}
