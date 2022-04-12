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
import jakarta.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String IS_USER_EXIST_FAILED = "User check is failed. Please, try to reapeat registration.";
	private final String USER_IS_NOT_VALID = "The entered data is not validat. Please fill the registration form below with correct data.";
	private final String REGISTRATION_FAILED = "Error: user registration failed. Please reapeat registration.";
	private final String REGISTRATION_SUCCESSFUL = "Registration is completed successfully. Please log in.";

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User userFromRegistrForm = buildUser(request);
		boolean isUserValid = isValid(userFromRegistrForm);
		if (!isUserValid) {
			request.setAttribute("registration_message", USER_IS_NOT_VALID);
			logger.log(Level.INFO, "User isn't valid.");
			return new Router(PathToPage.REGISTRATION, RouterType.FORWARD);
		}
		boolean isUserExists = false;
		try {
			isUserExists = userService.isExist(userFromRegistrForm);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't check is user exist in BD.", e);
			request.setAttribute("error_message", IS_USER_EXIST_FAILED);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}
		if (isUserExists) {
			request.setAttribute("registration_message",
					"User with login personnel number: " + userFromRegistrForm.getLoginPersonnelNumber()
							+ " has existed in BD yet. Please fill the registration form below with another loggin.");
			logger.log(Level.INFO, "User with loginPersonnelNumber: {} is exist in DB.",
					userFromRegistrForm.getLoginPersonnelNumber());
			return new Router(PathToPage.REGISTRATION, RouterType.FORWARD);
		}
		if (!isUserExists && isUserValid) {
			try {
				userService.registrate(userFromRegistrForm);
				request.setAttribute("registration_message", REGISTRATION_SUCCESSFUL);
				logger.log(Level.INFO, "userFromRegistrForm: {}", userFromRegistrForm.toString());
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "User registration is failed. User: {} ", userFromRegistrForm.toString(), e);
				request.setAttribute("error_message", REGISTRATION_FAILED);
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
		}
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return new Router(PathToPage.AUTHORIZATION, RouterType.FORWARD);
	}

	private boolean isValid(User user) {
		boolean isValid = userService.isValid(user);
		return isValid;
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
		logger.log(Level.INFO, "User is built. User: {}", user.toString());
		return user;
	}

	private String getParameterToCheck(String name, HttpServletRequest request) {
		final String parameter = request.getParameter(name);
		if (parameter == null) {
			logger.log(Level.ERROR, "Request have no parameter with name: {}" + name);
			throw new IllegalArgumentException("Request have no parameter with name: {}" + name);
		}
		return parameter;
	}
}
