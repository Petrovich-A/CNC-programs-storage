package by.petrovich.storage.validator.impl;

import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.validator.RegistrationUserInfoValidate;

public class RegistrationUserInfoValidator implements RegistrationUserInfoValidate {
	private static final Logger logger = LogManager.getLogger();
	private static final String LOGIN_PERSONNEL_NUMBER_PATTERN = "^\\p{Digit}{5,5}+$";
	private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z,A-Z])(?=.*[!?@#$%^&+=,;:_*()]).{8,40}$";
	private static final String EMPLOYEE_NAME_PATTERN = "^[\\p{IsAlphabetic}]{3,30}+$";
	private static final String EMPLOYEE_SURNAME_PATTERN = "^[\\p{IsAlphabetic}]{3,30}+$";
	private static final String EMPLOYEE_PATRONYMIC_PATTERN = "^[\\p{IsAlphabetic}]{3,30}+$";
	private static final String EMAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";

	private static RegistrationUserInfoValidator instance;

	private RegistrationUserInfoValidator() {

	}

	public static RegistrationUserInfoValidator getInstance() {
		if (instance == null) {
			instance = new RegistrationUserInfoValidator();
		}
		return instance;
	}

	@Override
	public boolean isRegistrationUserInfoValid(RegistrationUserInfo registrationUserInfo) {
		return isPersonnelNumberValid(String.valueOf(registrationUserInfo.getPersonnelNumber()))
				&& isEmployeeNameValid(registrationUserInfo.getEmployeeName())
				&& isEmployeeSurnameValid(registrationUserInfo.getEmployeeSurname())
				&& isEmployeePatronymicValid(registrationUserInfo.getEmployeePatronymic())
				&& isEmployeePositionValid((registrationUserInfo.getEmployeePosition().toString()))
				&& isEmailValid(registrationUserInfo.getEmail())
				&& isPasswordValid(registrationUserInfo.getPassword())
				&& isPasswordConfirmValid(registrationUserInfo.getConfirmPassword());
	}

	@Override
	public boolean isPersonnelNumberValid(String personnelNumber) {
		boolean isValid = Pattern.matches(LOGIN_PERSONNEL_NUMBER_PATTERN, personnelNumber);
		if (!isValid) {
			logger.log(Level.ERROR, "PersonnelNumber: {} is not valid", personnelNumber);
		}
		return isValid;
	}

	@Override
	public boolean isPasswordValid(String password) {
		boolean isValid = Pattern.matches(PASSWORD_PATTERN, password);
		if (!isValid) {
			logger.log(Level.ERROR, "password: {} is not valid", password);
		}
		return isValid;
	}

	@Override
	public boolean isEmployeeNameValid(String employeeName) {
		boolean isValid = employeeName.matches(EMPLOYEE_NAME_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "employeeName: {} is not valid: ", employeeName);
		}
		return isValid;
	}

	@Override
	public boolean isEmployeeSurnameValid(String employeeSurname) {
		boolean isValid = employeeSurname.matches(EMPLOYEE_SURNAME_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "employeeSurname: {} is not valid: ", employeeSurname);
		}
		return isValid;
	}

	@Override
	public boolean isEmployeePatronymicValid(String employeePatronymic) {
		boolean isValid = employeePatronymic.matches(EMPLOYEE_PATRONYMIC_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "employeePatronymic: {} is not valid: ", employeePatronymic);
		}
		return isValid;
	}

	@Override
	public boolean isEmployeePositionValid(String employeePosition) {
		boolean isValid = false;
		if (EmployeePosition.fromString(employeePosition) != null) {
			isValid = true;
			logger.log(Level.INFO, "Enum position is: {}", EmployeePosition.fromString(employeePosition));
		} else {
			logger.log(Level.ERROR, "Position: {} has no matches in enum",
					EmployeePosition.fromString(employeePosition));
			isValid = false;
		}
		return isValid;
	}

	@Override
	public boolean isEmailValid(String email) {
		boolean isValid = email.matches(EMAIL_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "email: {} is not valid: ", email);
		}
		return isValid;
	}

	@Override
	public boolean isPasswordConfirmValid(String passwordConfirm) {
		boolean isValid = passwordConfirm.matches(PASSWORD_PATTERN);
		if (!isValid) {
			logger.log(Level.ERROR, "passwordConfirm: {} is not valid", passwordConfirm);
		}
		return isValid;
	}

}