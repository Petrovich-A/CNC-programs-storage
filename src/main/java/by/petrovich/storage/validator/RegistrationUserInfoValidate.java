package by.petrovich.storage.validator;

import by.petrovich.storage.entity.RegistrationUserInfo;

/**
 * @author Petrovich A.V.
 *
 */
public interface RegistrationUserInfoValidate {
	/**
	 * @param registrationUserInfo
	 * @return
	 */
	boolean isRegistrationUserInfoValid(RegistrationUserInfo registrationUserInfo);

	/**
	 * @param personnelNumber
	 * @return
	 */
	boolean isPersonnelNumberValid(String personnelNumber);

	/**
	 * @param employeeName
	 * @return
	 */
	boolean isEmployeeNameValid(String employeeName);

	/**
	 * @param employeeSurname
	 * @return
	 */
	boolean isEmployeeSurnameValid(String employeeSurname);

	/**
	 * @param employeePatronymic
	 * @return
	 */
	boolean isEmployeePatronymicValid(String employeePatronymic);

	/**
	 * @param employeePosition
	 * @return
	 */
	boolean isEmployeePositionValid(String employeePosition);

	/**
	 * @param email
	 * @return
	 */
	boolean isEmailValid(String email);

	/**
	 * @param password
	 * @return
	 */
	boolean isPasswordValid(String password);

	/**
	 * @param passwordConfirm
	 * @return
	 */
	boolean isPasswordConfirmValid(String passwordConfirm);

}
