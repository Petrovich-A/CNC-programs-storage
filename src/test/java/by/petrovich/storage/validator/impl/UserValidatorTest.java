package by.petrovich.storage.validator.impl;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;

public class UserValidatorTest {
	UserValidator userValidator = UserValidator.getInstance();
	private int loginPersonnelNumber = 52914;
	private int loginPersonnelNumber1 = 2914;
	private String password = "ejfwdwdw1!";
	private String employeeName = "Иван";
	private String employeeSurname = "Иванов";
	private String employeePatronymic = "Иванович";
	private EmployeePosition employeePosition = EmployeePosition.CNC_PROGRAMMER;
	private String email = "ivanov@mail.by";
	private UserRole userRole = UserRole.USER;

	User userActual = new User(65484, "efwsef23!#", "Иван", "Иванов", "Рудольфович", EmployeePosition.CNC_PROGRAMMER,
			"ivanov@mail.ru");
	User userActual1 = new User(12345, "кпоулр!уаkh534", "John", "Smith", "Smitovich",
			EmployeePosition.ENGINEERING_TECHNICIAN, "america@usa.com");
	User userActual2 = new User(54218, "кпоу(efw:ef3234", "Слесарев", "Николай", "Иванович",
			EmployeePosition.CNC_PROGRAMMER, "bigBoss@gov.no");
	User userActualWithRole = new User(84576, "кпоswgv)wef234", "Ролевой", "Роман", "Иванович",
			EmployeePosition.CNC_PROGRAMMER, "withRole@sm.com");
	User userActual3 = new User(84566, "vwedfdfe", "Сунь", "Лин", "Иванович", EmployeePosition.CNC_PROGRAMMER,
			"lisymn@sm.com");

	@Test
	public void IsUserValidWhenUserValidReturnTrue() {
		boolean actual = userValidator.isUserValid(userActual);
		boolean actual1 = userValidator.isUserValid(userActual1);
		boolean actual2 = userValidator.isUserValid(userActual2);
		boolean actualWithRole = userValidator.isUserValid(userActualWithRole);
		boolean actual3 = userValidator.isUserValid(userActual3);
		Assert.assertTrue("test", actual);
		Assert.assertTrue("test1", actual1);
		Assert.assertTrue("test2", actual2);
		Assert.assertTrue("testWithRole", actualWithRole);
		Assert.assertTrue("actual3", actual3);
	}

	@Test
	public void isLoginPersonnelNumberValid() {
		boolean actualLoginPersonnelNumber = userValidator
				.isLoginPersonnelNumberValid(String.valueOf(loginPersonnelNumber));
		Assert.assertTrue("isUserValid", actualLoginPersonnelNumber);
		boolean actualLoginPersonnelNumber1 = userValidator
				.isLoginPersonnelNumberValid(String.valueOf(loginPersonnelNumber1));
		Assert.assertFalse("isUserValid", actualLoginPersonnelNumber1);
	}

	@Test
	public void isPasswordValid() {
		boolean actualPassword = userValidator.isPasswordValid(password);
		Assert.assertTrue("isPasswordValid", actualPassword);
	}

	@Test
	public void isEmployeeNameValid() {
		boolean actualEmployeeName = userValidator.isEmployeeNameValid(employeeName);
		Assert.assertTrue("isEmployeeNameValid", actualEmployeeName);
	}

	@Test
	public void isEmployeeSurnameValid() {
		boolean actualEmployeeSurname = userValidator.isEmployeeSurnameValid(employeeSurname);
		Assert.assertTrue("isEmployeeSurnameValid", actualEmployeeSurname);
	}

	@Test
	public void isEmployeePatronymicValid() {
		boolean actualEmployeePatronymic = userValidator.isEmployeePatronymicValid(employeePatronymic);
		Assert.assertTrue("isEmployeePatronymicValid", actualEmployeePatronymic);
	}

	@Test
	public void isEmployeePositionValid() {
		boolean actualEmployeePosition = userValidator.isEmployeePositionValid(employeePosition.toString());
		Assert.assertTrue("isEmployeePositionValid", actualEmployeePosition);
	}

	@Test
	public void isEmailValid() {
		boolean actualEmail = userValidator.isEmailValid(email);
		Assert.assertTrue("isEmailValid", actualEmail);
	}
}