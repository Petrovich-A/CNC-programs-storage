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
	private String passwordWithoutPunct = "efweferg2324";
	private String passwordWithoutDigit = "!!dgergergw";
	private String passwordWithoutAlpha = "2325%42123!";
	private String passwordLess = "rg15!%f";
	private String passwordMore = "145waasdf!wwf2efwef%#414sdfjkhsad987asfsafasf";
	private String passwordValid = "dfdg244!$";
	private String employeeName = "Иван";
	private String employeeSurname = "Иванов";
	private String employeePatronymic = "Иванович";
	private EmployeePosition employeePosition = EmployeePosition.CNC_PROGRAMMER;
	private String email = "ivanov@mail.by";
	private UserRole userRole = UserRole.USER;

	User userActual = new User(65484, "efwsef23%", "Иван", "Иванов", "Рудольфович", EmployeePosition.CNC_PROGRAMMER,
			"ivanov@mail.ru");
	User userActual1 = new User(12345, "nmverg24#%", "John", "Smith", "Smitovich",
			EmployeePosition.ENGINEERING_TECHNICIAN, "america@usa.com");
	User userActual2 = new User(54218, "easfav97!", "Слесарев", "Николай", "Иванович",
			EmployeePosition.CNC_PROGRAMMER, "bigBoss@gov.no");
	User userActualWithRole = new User(84576, "swgvf234$%", "Ролевой", "Роман", "Иванович",
			EmployeePosition.CNC_PROGRAMMER, "withRole@sm.com");
	User userActual3 = new User(84566, "vwkop5651%", "Сунь", "Лин", "Иванович", EmployeePosition.CNC_PROGRAMMER,
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
		boolean actualPassword = userValidator.isPasswordValid(passwordWithoutPunct);
		boolean actualPassword1 = userValidator.isPasswordValid(passwordWithoutDigit);
		boolean actualPassword2 = userValidator.isPasswordValid(passwordWithoutAlpha);
		boolean actualPassword3 = userValidator.isPasswordValid(passwordLess);
		boolean actualPassword4 = userValidator.isPasswordValid(passwordMore);
		boolean actualPassword5 = userValidator.isPasswordValid(passwordValid);
		Assert.assertFalse("", actualPassword);
		Assert.assertFalse("", actualPassword1);
		Assert.assertFalse("", actualPassword2);
		Assert.assertFalse("", actualPassword3);
		Assert.assertFalse("", actualPassword4);
		Assert.assertTrue("", actualPassword5);
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