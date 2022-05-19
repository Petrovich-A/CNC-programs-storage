package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.AUTHORIZATION_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.ERROR_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.REGISTRATION_PAGE;

import java.sql.Timestamp;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.RegistrationUserInfo;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @author Petrovich A.V.
 *
 */
public class RegistrationCommand extends AbstractCommand {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final String WRONG_INPUT_DATA = "Something went wrong. The entered data is wrong. Please input a correct data and try to reapeat registration again";
	private final String REGISTRATION_FAILED = "Error: user registration failed.";
	private final String REGISTRATION_SUCCESSFUL = "Registration is completed successfully. Please log in.";

	@Override
	public Router execute(HttpServletRequest request) {
		RegistrationUserInfo registrationUserInfo = buildRegistrationUserInfo(request);
		try {
			boolean isUserExists = userService.isUserExist(registrationUserInfo.getPersonnelNumber());
			boolean isPaswordsMatch = false;
			boolean isUserValid = false;
			if (!isUserExists) {
				isPaswordsMatch = userService.isPasswordsMatch(registrationUserInfo.getPassword(),
						registrationUserInfo.getConfirmPassword());
				isUserValid = userService.isValid(registrationUserInfo);
				if (isPaswordsMatch && isUserValid) {
					return registrate(request, registrationUserInfo);
				} else {
					logger.log(Level.INFO, "Registration user data is wrong.");
					return createRouterWithAttribute(request, REGISTRATION_PAGE, "registration_message", WRONG_INPUT_DATA);
				}
			} else {
				logger.log(Level.INFO, "User with personnel number: {} is yet exist in DB.",
						registrationUserInfo.getPersonnelNumber());
				String messagePlusPersonnelNumber = "User with personnel number: "
						+ registrationUserInfo.getPersonnelNumber()
						+ " has existed in BD yet. Please fill the registration form below with another login.";
				return createRouterWithAttribute(request, REGISTRATION_PAGE, "registration_message",
						messagePlusPersonnelNumber);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User registration failed.", e);
			return createRouterWithAttribute(request, ERROR_PAGE, "error_message", REGISTRATION_FAILED);
		}
	}

	/**
	 * @param request
	 * @param registrationUserInfo
	 * @return
	 * @throws ServiceException
	 */
	private Router registrate(HttpServletRequest request, RegistrationUserInfo registrationUserInfo)
			throws ServiceException {
		HttpSession session = request.getSession(true);
		userService.registrateUser(registrationUserInfo);
		logger.log(Level.INFO, "User registration is completed successfully. UserFromRegistrForm: {}",
				registrationUserInfo.toString());
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return createRouterWithAttribute(request, AUTHORIZATION_PAGE, "authorization_message", REGISTRATION_SUCCESSFUL);
	}

	/**
	 * @param request
	 * @return
	 */
	private RegistrationUserInfo buildRegistrationUserInfo(HttpServletRequest request) {
		RegistrationUserInfo registrationUserInfo = new RegistrationUserInfo.RegistrationUserInfoBuilder()
				.withPersonnelNumber(Integer.parseInt(getParameterToCheck("personnelNumber", request)))
				.withEmployeeName(getParameterToCheck("employeeName", request))
				.withEmployeeSurname(getParameterToCheck("employeeSurname", request))
				.withEmployeePatronymic(getParameterToCheck("employeePatronymic", request))
				.withEmployeePosition(EmployeePosition.fromString(getParameterToCheck("employeePosition", request)))
				.withEmail(getParameterToCheck("email", request)).withPassword(getParameterToCheck("password", request))
				.withConfirmPassword(getParameterToCheck("passwordConfirm", request))
				.with—reationDate(new Timestamp(System.currentTimeMillis())).build();
		logger.log(Level.INFO, "RegistrationUserInfo is built. User: {}", registrationUserInfo.toString());
		return registrationUserInfo;
	}

}
