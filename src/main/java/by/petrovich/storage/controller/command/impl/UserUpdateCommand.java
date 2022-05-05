package by.petrovich.storage.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserUpdateCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String UPDATE_USER_SUCCESSFUL = "User updating is successful";
	private static final String UPDATE_USER_FAILD = "User updating is faild";
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User userFromUpdateForm = buildUser(request);
		int loginPersonnelNumber = (Integer) session.getAttribute("loginPersonnelNumber");
		try {
			userService.update(userFromUpdateForm, loginPersonnelNumber);
			request.setAttribute("admin_users_message", UPDATE_USER_SUCCESSFUL);
			logger.log(Level.INFO, "user with loginPersonnelNumber: {} is updated", loginPersonnelNumber);
			return new Router(PathToPage.ADMIN_USERS, RouterType.REDIRECT);
		} catch (ServiceException e) {
			request.setAttribute("message", UPDATE_USER_FAILD);
			logger.log(Level.ERROR, "can't update user with loginPersonnelNumber: {}, user: {}", loginPersonnelNumber,
					userFromUpdateForm.toString(), e);
			return new Router(PathToPage.USER_UPDATE, RouterType.FORWARD);
		}
	}

	private User buildUser(HttpServletRequest request) {
		User user = new User();
		user.setEmployeeName(getParameterToCheck("employeeName", request));
		user.setEmployeeSurname(getParameterToCheck("employeeSurname", request));
		user.setEmployeePatronymic(getParameterToCheck("employeePatronymic", request));
		user.setEmployeePosition(EmployeePosition.fromString(getParameterToCheck("employeePosition", request)));
		user.setUserRole(UserRole.fromString(getParameterToCheck("userRole", request)));
		user.setEmail(getParameterToCheck("email", request));
		logger.log(Level.INFO, "buildUser: {}", user.toString());
		return user;
	}

	private String getParameterToCheck(String name, HttpServletRequest request) {
		final String parameter = request.getParameter(name);
		if (parameter == null) {
			logger.log(Level.ERROR, "request have no parameter with name: {}" + name);
			throw new IllegalArgumentException("request have no parameter with name: {}" + name);
		}
		return parameter;
	}

}
