package by.petrovich.storage.validator.impl;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.UserRole;

public class RegistrationUserInfoValidatorTest {
	RegistrationUserInfoValidator registrationUserInfoValidator = RegistrationUserInfoValidator.getInstance();
	private int personnelNumber = 42000;
	private int personnelNumberLessSymbols = 1234;
	private int personnelNumberMoreSymbols = 123456;
	private int personnelNumberZero = 0;
	private String passwordWithoutPunct = "efweferg2324";
	private String passwordWithoutDigit = "!!dgergergw";
	private String passwordWithoutAlpha = "2325%42123!";
	private String passwordLessSymbols = "rg15!%f";
	private String passwordMoreSymbols = "145waasdf!wwf2efwef%#414sdfjkhsad987asfsafasf";
	private String passwordValid = "efefe!2d";
	private String employeeName = "Иван";
	private String employeeSurname = "Иванов";
	private String employeePatronymic = "Иванович";
	private EmployeePosition employeePosition = EmployeePosition.CNC_PROGRAMMER;
	private String email = "ivanov@mail.by";
	private UserRole userRole = UserRole.USER;
	private RegistrationUserInfo registrationUserInfoValid = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(65000).withEmployeeName("Иван").withEmployeeSurname("Иванов")
			.withEmployeePatronymic("Рудольфович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withEmail("ivanov@mail.ru").withPassword("efwsef23%").withConfirmPassword("efwsef23%").build();
	private RegistrationUserInfo registrationUserInfoActual1 = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(12000).withEmployeeName("John").withEmployeeSurname("Smith")
			.withEmployeePatronymic("Smitovich").withEmployeePosition(EmployeePosition.ENGINEERING_TECHNICIAN)
			.withEmail("america@usa.com").withPassword("nmverg24#%").withConfirmPassword("nmverg24#%").build();
	private RegistrationUserInfo registrationUserInfoActual2 = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(54200).withEmployeeName("Слесарев").withEmployeeSurname("Николай")
			.withEmployeePatronymic("Иванович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withEmail("bigBoss@gov.no").withPassword("easfav97!").withConfirmPassword("easfav97!").build();
	private RegistrationUserInfo registrationUserInfoActualWithRole = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(12400).withEmployeeName("Ролевой").withEmployeeSurname("Роман")
			.withEmployeePatronymic("Романович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withEmail("withRole@sm.com").withPassword("swgvf234$%").withConfirmPassword("swgvf234$%")
			.withUserRole(userRole).build();

	@Test
	public void isUserValidWhenUserValidReturnTrue() {
		boolean actual = registrationUserInfoValidator.isRegistrationUserInfoValid(registrationUserInfoValid);
		System.out.println(registrationUserInfoValid.toString());
		Assert.assertTrue("test", actual);
	}

	@Test
	public void isPersonnelNumberValid() {
		boolean actualPersonnelNumber = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(personnelNumber));
		boolean actualPersonnelNumber1 = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(personnelNumberLessSymbols));
		boolean actualPersonnelNumber2 = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(personnelNumberMoreSymbols));
		boolean actualPersonnelNumber3 = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(personnelNumberZero));
		Assert.assertTrue("isPersonnelNumberValid", actualPersonnelNumber);
		Assert.assertFalse("isPersonnelNumberValid", actualPersonnelNumber1);
		Assert.assertFalse("isPersonnelNumberValid", actualPersonnelNumber2);
		Assert.assertFalse("isPersonnelNumberValid", actualPersonnelNumber3);
	}

	@Test
	public void isPasswordValid() {
		boolean actualPassword = registrationUserInfoValidator.isPasswordValid(passwordValid);
		boolean actualPassword1 = registrationUserInfoValidator.isPasswordValid(passwordWithoutPunct);
		boolean actualPassword2 = registrationUserInfoValidator.isPasswordValid(passwordWithoutDigit);
		boolean actualPassword3 = registrationUserInfoValidator.isPasswordValid(passwordWithoutAlpha);
		boolean actualPassword4 = registrationUserInfoValidator.isPasswordValid(passwordLessSymbols);
		boolean actualPassword5 = registrationUserInfoValidator.isPasswordValid(passwordMoreSymbols);
		Assert.assertTrue("", actualPassword);
		Assert.assertFalse("", actualPassword1);
		Assert.assertFalse("", actualPassword2);
		Assert.assertFalse("", actualPassword3);
		Assert.assertFalse("", actualPassword4);
		Assert.assertFalse("", actualPassword5);
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