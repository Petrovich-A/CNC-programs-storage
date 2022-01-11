package by.petrovich.storage.validator.impl;

import by.petrovich.storage.entity.User;
import by.petrovich.storage.validator.UserValidatable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidator implements UserValidatable {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_PERSONNEL_NUMBER_PATTERN = "^\\d{5}+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,40}$";
    private static final String EMPLOYEE_NAME_PATTERN = "^[A-Z,a-z,А-Я,а-я]{3,20}+$";
    private static final String EMPLOYEE_SURNAME_PATTERN = "^[A-Z,a-z,А-Я,а-я]{3,20}+$";
    private static final String EMPLOYEE_PATRONYMIC_PATTERN = "^[A-Z,a-z,А-Я,а-я]{3,20}+$";
    private static final String POSITION_PATTERN = "^[A-Z,a-z,А-Я,а-я]{3,30}+$";
    private static final String EMAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";

    @Override
    public boolean isUserValid(User user) {
        return isLoginPersonnelNumberValid(String.valueOf(user.getLoginPersonnelNumber())) && isPasswordValid(user.getPassword())
                && isEmployeeNameValid(user.getEmployeeName()) && isEmployeeSurnameValid(user.getEmployeeSurname())
                && isEmployeePatronymicValid(user.getEmployeePatronymic()) && isPositionValid(user.getPosition())
                && isEmailValid(user.getEmail());
    }

    private boolean isPasswordValid(String password) {
        boolean isValid = false;
        if (password.matches(PASSWORD_PATTERN)) {
            isValid = true;
        } else {
            logger.log(Level.DEBUG, "password is not valid: ", isValid);
        }
        return isValid;
    }

    private boolean isLoginPersonnelNumberValid(String loginPersonnelNumber) {
        boolean isValid = false;
        if (loginPersonnelNumber.matches(LOGIN_PERSONNEL_NUMBER_PATTERN)) {
            isValid = true;
        } else {
            logger.log(Level.ERROR, "loginPersonnelNumber is not valid: ", isValid);
        }
        return isValid;
    }

    private boolean isEmployeeNameValid(String employeeName) {
        boolean isValid = false;
        if (employeeName.matches(EMPLOYEE_NAME_PATTERN)) {
            isValid = true;
        } else {
            logger.log(Level.ERROR, "employeeName is not valid: ", isValid);
        }
        return isValid;
    }

    private boolean isEmployeeSurnameValid(String employeeSurname) {
        boolean isValid = false;
        if (employeeSurname.matches(EMPLOYEE_SURNAME_PATTERN)) {
            isValid = true;
        } else {
            logger.log(Level.ERROR, "employeeSurname is not valid: ", isValid);
        }
        return isValid;
    }

    private boolean isEmployeePatronymicValid(String employeePatronymic) {
        boolean isValid = false;
        if (employeePatronymic.matches(EMPLOYEE_PATRONYMIC_PATTERN)) {
            isValid = true;
        } else {
            logger.log(Level.ERROR, "employeePatronymic is not valid: ", isValid);
        }
        return isValid;
    }

    private boolean isPositionValid(String position) {
        boolean isValid = false;
        if (position.matches(POSITION_PATTERN)) {
            isValid = true;
        } else {
            logger.log(Level.ERROR, "position is not valid: ", isValid);
        }
        return isValid;
    }

    private boolean isEmailValid(String email) {
        boolean isValid = false;
        if (email.matches(EMAIL_PATTERN)) {
            isValid = true;
        } else {
            logger.log(Level.ERROR, "email is not valid: ", isValid);
        }
        return isValid;
    }

}