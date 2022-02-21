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
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String REGISTRATION_SUCCESSFUL = "Registration is completed successfully! Please log in.";
	private final String REGISTRATION_FAILED = "Error: user registration failed. Please reapeat registration.";

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		User userFromRegistrForm = buildUser(request);
		HttpSession session = request.getSession(true);
		try {
			request.setAttribute("userFromRegistrForm", userFromRegistrForm);
			userService.registrate(userFromRegistrForm);
			session.setAttribute("message", REGISTRATION_SUCCESSFUL);
			logger.log(Level.INFO, "userFromRegistrForm: {}", userFromRegistrForm.toString());
			return new Router(PathToPage.AUTHORIZATION, RouterType.FORWARD);
		} catch (ServiceException e) {
			session.setAttribute("message", REGISTRATION_FAILED);
			logger.log(Level.ERROR, e.getLocalizedMessage(), e);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
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
		user.setEmail(getParameterToCheck("email", request));
		user.setCreationDate(timestamp);
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
