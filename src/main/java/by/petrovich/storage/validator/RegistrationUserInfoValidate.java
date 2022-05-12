package by.petrovich.storage.validator;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;

public interface RegistrationUserInfoValidate {
	boolean isRegistrationUserInfoValid(RegistrationUserInfo registrationUserInfo);

	boolean isPersonnelNumberValid(String personnelNumber);

	boolean isEmployeeNameValid(String employeeName);

	boolean isEmployeeSurnameValid(String employeeSurname);

	boolean isEmployeePatronymicValid(String employeePatronymic);

	boolean isEmployeePositionValid(String employeePosition);

	boolean isEmailValid(String email);

	boolean isPasswordValid(String password);

	boolean isPasswordConfirmValid(String passwordConfirm);

}
