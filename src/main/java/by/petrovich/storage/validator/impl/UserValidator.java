package by.petrovich.storage.validator.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.validator.UserValidate;

public class UserValidator implements UserValidate {
	private static final Logger logger = LogManager.getLogger();
	private static final String LOGIN_PERSONNEL_NUMBER_PATTERN = "^\\p{Digit}{5}+$";
	private static final String PASSWORD_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}\\p{Punct}]{8,40}+$";
	private static final String EMPLOYEE_NAME_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}]{3,30}+$";
	private static final String EMPLOYEE_SURNAME_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}]{3,30}+$";
	private static final String EMPLOYEE_PATRONYMIC_PATTERN = "^[\\p{IsAlphabetic}\\p{IsDigit}]{3,30}+$";
	private static final String EMAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";

	private static UserValidator instance;

	private UserValidator() {

	}

	public static UserValidator getInstance() {
		if (instance == null) {
			instance = new UserValidator();
		}
		return instance;
	}

	@Override
	public boolean isUserValid(User user) {
		return isLoginPersonnelNumberValid(String.valueOf(user.getLoginPersonnelNumber()))
				&& isPasswordValid(user.getPassword()) && isEmployeeNameValid(user.getEmployeeName())
				&& isEmployeeSurnameValid(user.getEmployeeSurname())
				&& isEmployeePatronymicValid(user.getEmployeePatronymic()) && isPositionValid(user.getPosition())
				&& isEmailValid(user.getEmail());
	}

	@Override
	public boolean isLoginPersonnelNumberValid(String loginPersonnelNumber) {
		boolean isValid = loginPersonnelNumber.matches(LOGIN_PERSONNEL_NUMBER_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "loginPersonnelNumber is not valid: ", loginPersonnelNumber);
		}
		return isValid;
	}

	@Override
	public boolean isPasswordValid(String password) {
		boolean isValid = password.matches(PASSWORD_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "password is not valid");
		}
		return isValid;
	}

	@Override
	public boolean isEmployeeNameValid(String employeeName) {
		boolean isValid = employeeName.matches(EMPLOYEE_NAME_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "employeeName is not valid: ", employeeName);
		}
		return isValid;
	}

	@Override
	public boolean isEmployeeSurnameValid(String employeeSurname) {
		boolean isValid = employeeSurname.matches(EMPLOYEE_SURNAME_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "employeeSurname is not valid: ", employeeSurname);
		}
		return isValid;
	}

	@Override
	public boolean isEmployeePatronymicValid(String employeePatronymic) {
		boolean isValid = employeePatronymic.matches(EMPLOYEE_PATRONYMIC_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "employeePatronymic is not valid: ", employeePatronymic);
		}
		return isValid;
	}

	@Override
	public boolean isPositionValid(String position) {
		boolean isValid = false;
		if (EmployeePosition.fromString(position) != null) {
			isValid = true;
			logger.log(Level.DEBUG, "Enum position is: {}", EmployeePosition.fromString(position));
		} else {
			logger.log(Level.ERROR, "Position: {} has no matches in enum", position);
			isValid = false;
		}
		return isValid;
	}

	@Override
	public boolean isEmailValid(String email) {
		boolean isValid = email.matches(EMAIL_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "email is not valid: ", email);
		}
		return isValid;
	}

}