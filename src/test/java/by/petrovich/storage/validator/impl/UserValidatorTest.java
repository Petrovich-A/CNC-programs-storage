package by.petrovich.storage.validator.impl;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.UserRole;

public class UserValidatorTest {
	RegistrationUserInfoValidator registrationUserInfoValidator = RegistrationUserInfoValidator.getInstance();
	private int loginPersonnelNumber = 52914;
	private int loginPersonnelNumber1 = 2914;
	private String passwordWithoutPunct = "efweferg2324";
	private String passwordWithoutDigit = "!!dgergergw";
	private String passwordWithoutAlpha = "2325%42123!";
	private String passwordLess = "rg15!%f";
	private String passwordMore = "145waasdf!wwf2efwef%#414sdfjkhsad987asfsafasf";
	private String passwordValid = "efefe!2d";
	private String employeeName = "Иван";
	private String employeeSurname = "Иванов";
	private String employeePatronymic = "Иванович";
	private EmployeePosition employeePosition = EmployeePosition.CNC_PROGRAMMER;
	private String email = "ivanov@mail.by";
	private UserRole userRole = UserRole.USER;

	RegistrationUserInfo registrationUserInfoActual = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(65484).withEmployeeName("Иван").withEmployeeSurname("Иванов")
			.withEmployeePatronymic("Рудольфович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withPassword("efwsef23%").withEmail("ivanov@mail.ru").build();

	RegistrationUserInfo registrationUserInfoActual1 = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(12345).withEmployeeName("John").withEmployeeSurname("Smith")
			.withEmployeePatronymic("Smitovich").withEmployeePosition(EmployeePosition.ENGINEERING_TECHNICIAN)
			.withPassword("nmverg24#%").withEmail("america@usa.com").build();

	RegistrationUserInfo registrationUserInfoActual2 = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(54218).withEmployeeName("Слесарев").withEmployeeSurname("Николай")
			.withEmployeePatronymic("Иванович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withPassword("easfav97!").withEmail("bigBoss@gov.no").build();

	RegistrationUserInfo registrationUserInfoActualWithRole = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(84576).withEmployeeName("Ролевой").withEmployeeSurname("Роман")
			.withEmployeePatronymic("Романович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withPassword("swgvf234$%").withEmail("withRole@sm.com").build();

	@Test
	public void IsUserValidWhenUserValidReturnTrue() {
		boolean actual = registrationUserInfoValidator.isRegistrationUserInfoValid(registrationUserInfoActual);
		boolean actual1 = registrationUserInfoValidator.isRegistrationUserInfoValid(registrationUserInfoActual1);
		boolean actual2 = registrationUserInfoValidator.isRegistrationUserInfoValid(registrationUserInfoActual2);
		boolean actualWithRole = registrationUserInfoValidator
				.isRegistrationUserInfoValid(registrationUserInfoActualWithRole);
		Assert.assertTrue("test", actual);
		Assert.assertTrue("test1", actual1);
		Assert.assertTrue("test2", actual2);
		Assert.assertTrue("testWithRole", actualWithRole);
	}

	@Test
	public void isLoginPersonnelNumberValid() {
		boolean actualLoginPersonnelNumber = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(loginPersonnelNumber));
		Assert.assertTrue("isUserValid", actualLoginPersonnelNumber);
		boolean actualLoginPersonnelNumber1 = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(loginPersonnelNumber1));
		Assert.assertFalse("isUserValid", actualLoginPersonnelNumber1);
	}

	@Test
	public void isPasswordValid() {
		boolean actualPassword = registrationUserInfoValidator.isPasswordValid(passwordWithoutPunct);
		boolean actualPassword1 = registrationUserInfoValidator.isPasswordValid(passwordWithoutDigit);
		boolean actualPassword2 = registrationUserInfoValidator.isPasswordValid(passwordWithoutAlpha);
		boolean actualPassword3 = registrationUserInfoValidator.isPasswordValid(passwordLess);
		boolean actualPassword4 = registrationUserInfoValidator.isPasswordValid(passwordMore);
		boolean actualPassword5 = registrationUserInfoValidator.isPasswordValid(passwordValid);
		Assert.assertFalse("", actualPassword);
		Assert.assertFalse("", actualPassword1);
		Assert.assertFalse("", actualPassword2);
		Assert.assertFalse("", actualPassword3);
		Assert.assertFalse("", actualPassword4);
		Assert.assertTrue("", actualPassword5);
	}

	@Test
	public void isEmployeeNameValid() {
		boolean actualEmployeeName = registrationUserInfoValidator.isEmployeeNameValid(employeeName);
		Assert.assertTrue("isEmployeeNameValid", actualEmployeeName);
	}

	@Test
	public void isEmployeeSurnameValid() {
		boolean actualEmployeeSurname = registrationUserInfoValidator.isEmployeeSurnameValid(employeeSurname);
		Assert.assertTrue("isEmployeeSurnameValid", actualEmployeeSurname);
	}

	@Test
	public void isEmployeePatronymicValid() {
		boolean actualEmployeePatronymic = registrationUserInfoValidator.isEmployeePatronymicValid(employeePatronymic);
		Assert.assertTrue("isEmployeePatronymicValid", actualEmployeePatronymic);
	}

	@Test
	public void isEmployeePositionValid() {
		boolean actualEmployeePosition = registrationUserInfoValidator
				.isEmployeePositionValid(employeePosition.toString());
		Assert.assertTrue("isEmployeePositionValid", actualEmployeePosition);
	}

	@Test
	public void isEmailValid() {
		boolean actualEmail = registrationUserInfoValidator.isEmailValid(email);
		Assert.assertTrue("isEmailValid", actualEmail);
	}
}