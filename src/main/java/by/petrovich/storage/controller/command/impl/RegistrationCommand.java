package by.petrovich.storage.controller.command.impl;

import java.io.IOException;
import java.sql.Timestamp;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String SUCCESSFUL_REGISTRATION = "Registration is completed successfully! Please log in.";
	private final String REGISTRATION_FAILED = "Registration is completed successfully! Please log in.";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		User userFromRegistrForm = buildUser(request);
		HttpSession session = request.getSession(true);
		try {
			logger.log(Level.DEBUG, "user from registr form is received", userFromRegistrForm.toString());
			userFromRegistrForm.setUserRole(UserRole.USER);
			request.getSession(true).setAttribute("userFromRegistrForm from UI", userFromRegistrForm);
			userService.register(userFromRegistrForm);
			session.setAttribute("message", SUCCESSFUL_REGISTRATION);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.LOG_IN);
			requestDispatcher.forward(request, response);
		} catch (ServiceException e) {
			session.setAttribute("message", REGISTRATION_FAILED);
			logger.log(Level.ERROR, e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.log(Level.ERROR, e.getLocalizedMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
		user.setPosition(getParameterToCheck("employeePosition", request));
		user.setEmail(getParameterToCheck("email", request));
		user.setTimestamp(timestamp);
		return user;
	}

	private String getParameterToCheck(String name, HttpServletRequest request) {
		final String parameter = request.getParameter(name);
		if (parameter == null) {
			throw new IllegalArgumentException("request have no parameter with name: " + name);
		}
		return parameter;
	}
}
