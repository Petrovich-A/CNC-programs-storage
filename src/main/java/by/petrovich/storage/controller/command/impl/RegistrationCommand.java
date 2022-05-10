package by.petrovich.storage.controller.command.impl;

import java.sql.Timestamp;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.EmployeePosition;
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
		RegistrationUserInfo registrationUserInfo = buildRegistrationUserInfo(request);
		boolean isUserValid = isValid(registrationUserInfo);
		if (!isUserValid) {
			request.setAttribute("registration_message", USER_IS_NOT_VALID);
			logger.log(Level.INFO, "User isn't valid.");
			return new Router(PathToPage.REGISTRATION, RouterType.FORWARD);
		}
		boolean isUserExists = false;
		try {
			isUserExists = userService.isUserExist(registrationUserInfo.getPersonnelNumber());
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Can't check is user exist in BD.", e);
			request.setAttribute("error_message", IS_USER_EXIST_FAILED);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		}
		if (isUserExists) {
			request.setAttribute("registration_message",
					"User with personnel number: " + registrationUserInfo.getPersonnelNumber()
							+ " has existed in BD yet. Please fill the registration form below with another login.");
			logger.log(Level.INFO, "User with personnel number: {} is exist in DB.",
					registrationUserInfo.getPersonnelNumber());
			return new Router(PathToPage.REGISTRATION, RouterType.FORWARD);
		}
		if (!isUserExists && isUserValid) {
			try {
				userService.registrateUser(registrationUserInfo);
				request.setAttribute("registration_message", REGISTRATION_SUCCESSFUL);
				logger.log(Level.INFO, "userFromRegistrForm: {}", registrationUserInfo.toString());
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "User registration is failed. User: {} ", registrationUserInfo.toString(), e);
				request.setAttribute("error_message", REGISTRATION_FAILED);
				return new Router(PathToPage.ERROR, RouterType.FORWARD);
			}
		}
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return new Router(PathToPage.AUTHORIZATION, RouterType.FORWARD);
	}

	private boolean isValid(RegistrationUserInfo registrationUserInfo) {
		boolean isValid = userService.isValid(registrationUserInfo);
		return isValid;
	}

	private RegistrationUserInfo buildRegistrationUserInfo(HttpServletRequest request) {
		RegistrationUserInfo registrationUserInfo = new RegistrationUserInfo.RegistrationUserInfoBuilder()
				.withPersonnelNumber(Integer.parseInt(getParameterToCheck("loginPersonnelNumber", request)))
				.withEmployeeName(getParameterToCheck("employeeName", request))
				.withEmployeeSurname(getParameterToCheck("employeeSurname", request))
				.withEmployeePatronymic(getParameterToCheck("employeePatronymic", request))
				.withEmployeePosition(EmployeePosition.fromString(getParameterToCheck("employeePosition", request)))
				.withEmail(getParameterToCheck("email", request)).withPassword(getParameterToCheck("password", request))
				.withConfirmPassword(getParameterToCheck("passwordConfirm", request))
				.withTimestamp(new Timestamp(System.currentTimeMillis())).build();
		logger.log(Level.INFO, "RegistrationUserInfo is built. User: {}", registrationUserInfo.toString());
		return registrationUserInfo;
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
