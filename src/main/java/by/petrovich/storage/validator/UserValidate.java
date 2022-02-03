package by.petrovich.storage.validator;

import by.petrovich.storage.entity.User;

public interface UserValidate {
	boolean isUserValid(User user);

	boolean isLoginPersonnelNumberValid(String loginPersonnelNumber);

	boolean isPasswordValid(String password);

	boolean isEmployeeNameValid(String employeeName);

	boolean isEmployeeSurnameValid(String employeeSurname);

	boolean isEmployeePatronymicValid(String employeePatronymic);

	boolean isEmailValid(String email);

	boolean isEmployeePositionValid(String employeePosition);

}
