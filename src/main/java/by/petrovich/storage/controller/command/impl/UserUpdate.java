package by.petrovich.storage.controller.command.impl;

import java.sql.Timestamp;

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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserUpdate implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String UPDATE_USER_SUCCESSFUL = "user updating is successful";
	private final String UPDATE_USER_FAILD = "user updating is faild";

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		User userFromUpdateForm = buildUser(request);
		HttpSession session = request.getSession(true);
		User userFromSession = (User) session.getAttribute("user");
		try {
			userService.update(userFromUpdateForm, userFromSession.getLoginPersonnelNumber());
			session.setAttribute("message", UPDATE_USER_SUCCESSFUL);
			logger.log(Level.DEBUG, "user with loginPersonnelNumber: {} is updated",
					userFromSession.getLoginPersonnelNumber());
			return new Router(PathToPage.ADMIN_USERS, RouterType.FORWARD);
		} catch (ServiceException e) {
			session.setAttribute("message", UPDATE_USER_FAILD);
			logger.log(Level.DEBUG, "can't update user with loginPersonnelNumber: {}, user: {}",
					userFromSession.getLoginPersonnelNumber(), userFromUpdateForm.toString(), e);
			return new Router(PathToPage.USER_UPDATE, RouterType.FORWARD);
		}
	}

	private User buildUser(HttpServletRequest request) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = new User();
		user.setLoginPersonnelNumber(Integer.parseInt(getParameterToCheck("loginPersonnelNumber", request)));
		user.setPassword(getParameterToCheck("password", request));
		user.setEmployeeName(getParameterToCheck("employeeName", request));
		user.setEmployeeSurname(getParameterToCheck("employeeSurname", request));
		user.setEmployeePatronymic(getParameterToCheck("employeePatronymic", request));
		user.setEmployeePosition(EmployeePosition.fromString(getParameterToCheck("employeePosition", request)));
		user.setUserRole(UserRole.fromString(getParameterToCheck("userRole", request)));
		user.setEmail(getParameterToCheck("email", request));
		user.setCreationDate(timestamp);
		logger.log(Level.DEBUG, "buildUser: {}", user.toString());
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
