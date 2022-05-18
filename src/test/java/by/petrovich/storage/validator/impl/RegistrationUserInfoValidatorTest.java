/**
 * 
 */
package by.petrovich.storage.validator.impl;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.UserRole;

/**
 * @author Petrovich A.V.
 *
 */
public class RegistrationUserInfoValidatorTest {
	RegistrationUserInfoValidator registrationUserInfoValidator = RegistrationUserInfoValidator.getInstance();
	private int personnelNumberValid = 42000;
	private int personnelNumberInvalidLessSymbols = 1234;
	private int personnelNumberInvalidMoreSymbols = 123456;
	private int personnelNumberInvalidZero = 0;
	private String passwordValid = "efefe!2d";
	private String passwordInvalidWithoutPunct = "efweferg2324";
	private String passwordInvalidWithoutDigit = "!!dgergergw";
	private String passwordInvalidWithoutAlpha = "2325%42123!";
	private String passwordInvalidLessSymbols = "rg15!%f";
	private String passwordInvalidMoreSymbols = "145waasdf!wwf2efwef%#414sdfjkhsad987asfsafasf";
	private String employeeNameValid = "Иван";
	private String employeeNameInvalidLessSymbols = "Ли";
	private String employeeNameInvalidMoreSymbols = "Янезнаюоченьдлинногоимениукплукрп";
	private String employeeSurname = "Иванов";
	private String employeePatronymic = "Иванович";
	private String emailValid = "ivanov@mail.by";
	private String emailInvalid = "ivanovmail.by";
	private RegistrationUserInfo registrationUserInfoValid = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(65000).withEmployeeName("Иван").withEmployeeSurname("Иванов")
			.withEmployeePatronymic("Рудольфович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withEmail("ivanov@mail.ru").withPassword("efwsef23%").withConfirmPassword("efwsef23%").build();
	private RegistrationUserInfo registrationUserInfoInvalidPassword = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(12000).withEmployeeName("John").withEmployeeSurname("Smith")
			.withEmployeePatronymic("Smitovich").withEmployeePosition(EmployeePosition.ENGINEERING_TECHNICIAN)
			.withEmail("america@usa.com").withPassword("nmverg24").withConfirmPassword("nmverg24").build();
	private RegistrationUserInfo registrationUserInfoInvalidPersonnelNumber = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(100).withEmployeeName("Слесарев").withEmployeeSurname("Николай")
			.withEmployeePatronymic("Иванович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withEmail("bigBoss@gov.no").withPassword("easfav97!").withConfirmPassword("easfav97!").build();
	private RegistrationUserInfo registrationUserInfoWithRole = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(12400).withEmployeeName("Ролевой").withEmployeeSurname("Роман")
			.withEmployeePatronymic("Романович").withEmployeePosition(EmployeePosition.CNC_PROGRAMMER)
			.withEmail("withRole@sm.com").withPassword("swgvf234$%").withConfirmPassword("swgvf234$%")
			.withUserRole(UserRole.GUEST).build();

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isRegistrationUserInfoValid(by.petrovich.storage.entity.RegistrationUserInfo)}.
	 */
	@Test
	public void testIsRegistrationUserInfoValid() {
		boolean positive = registrationUserInfoValidator.isRegistrationUserInfoValid(registrationUserInfoValid);
		boolean positiveWithRole = registrationUserInfoValidator
				.isRegistrationUserInfoValid(registrationUserInfoWithRole);
		boolean negativeInvalidPass = registrationUserInfoValidator
				.isRegistrationUserInfoValid(registrationUserInfoInvalidPassword);
		boolean negativeInvalidPersonnelNum = registrationUserInfoValidator
				.isRegistrationUserInfoValid(registrationUserInfoInvalidPersonnelNumber);
		Assert.assertTrue(positive);
		Assert.assertTrue(positiveWithRole);
		Assert.assertFalse(negativeInvalidPass);
		Assert.assertFalse(negativeInvalidPersonnelNum);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isPersonnelNumberValid(java.lang.String)}.
	 */
	@Test
	public void testIsPersonnelNumberValid() {
		boolean positive = registrationUserInfoValidator.isPersonnelNumberValid(String.valueOf(personnelNumberValid));
		boolean negativeLess = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(personnelNumberInvalidLessSymbols));
		boolean negativeMore = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(personnelNumberInvalidMoreSymbols));
		boolean negativeZero = registrationUserInfoValidator
				.isPersonnelNumberValid(String.valueOf(personnelNumberInvalidZero));
		Assert.assertTrue(positive);
		Assert.assertFalse(negativeLess);
		Assert.assertFalse(negativeMore);
		Assert.assertFalse(negativeZero);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isPasswordValid(java.lang.String)}.
	 */
	@Test
	public void testIsPasswordValid() {
		boolean positive = registrationUserInfoValidator.isPasswordValid(passwordValid);
		boolean negativePasswordWithoutPunct = registrationUserInfoValidator
				.isPasswordValid(passwordInvalidWithoutPunct);
		boolean negativePasswordWithoutDigit = registrationUserInfoValidator
				.isPasswordValid(passwordInvalidWithoutDigit);
		boolean negativePasswordWithoutAlpha = registrationUserInfoValidator
				.isPasswordValid(passwordInvalidWithoutAlpha);
		boolean negativePasswordLessSymbols = registrationUserInfoValidator.isPasswordValid(passwordInvalidLessSymbols);
		boolean negativePasswordMoreSymbols = registrationUserInfoValidator.isPasswordValid(passwordInvalidMoreSymbols);
		Assert.assertTrue(positive);
		Assert.assertFalse(negativePasswordWithoutPunct);
		Assert.assertFalse(negativePasswordWithoutDigit);
		Assert.assertFalse(negativePasswordWithoutAlpha);
		Assert.assertFalse(negativePasswordLessSymbols);
		Assert.assertFalse(negativePasswordMoreSymbols);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isEmployeeNameValid(java.lang.String)}.
	 */
	@Test
	public void testIsEmployeeNameValid() {
		boolean positive = registrationUserInfoValidator.isEmployeeNameValid(employeeNameValid);
		boolean negativeLess = registrationUserInfoValidator.isEmployeeNameValid(employeeNameInvalidLessSymbols);
		boolean negativeMore = registrationUserInfoValidator.isEmployeeNameValid(employeeNameInvalidMoreSymbols);
		Assert.assertTrue(positive);
		Assert.assertFalse(negativeLess);
		Assert.assertFalse(negativeMore);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isEmployeeSurnameValid(java.lang.String)}.
	 */
	@Test
	public void testIsEmployeeSurnameValid() {
		boolean positive = registrationUserInfoValidator.isEmployeeSurnameValid(employeeSurname);
		Assert.assertTrue(positive);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isEmployeePatronymicValid(java.lang.String)}.
	 */
	@Test
	public void testIsEmployeePatronymicValid() {
		boolean positive = registrationUserInfoValidator.isEmployeePatronymicValid(employeePatronymic);
		Assert.assertTrue(positive);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isEmployeePositionValid(java.lang.String)}.
	 */
	@Test
	public void testIsEmployeePositionValid() {
		boolean positive = registrationUserInfoValidator
				.isEmployeePositionValid(EmployeePosition.ENGINEERING_TECHNICIAN.toString());
		boolean positive1 = registrationUserInfoValidator
				.isEmployeePositionValid(EmployeePosition.CNC_PROGRAMMER.toString());
		Assert.assertTrue(positive);
		Assert.assertTrue(positive1);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isEmailValid(java.lang.String)}.
	 */
	@Test
	public void testIsEmailValid() {
		boolean positive = registrationUserInfoValidator.isEmailValid(emailValid);
		boolean negative = registrationUserInfoValidator.isEmailValid(emailInvalid);
		Assert.assertTrue(positive);
		Assert.assertFalse(negative);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.validator.impl.RegistrationUserInfoValidator#isPasswordConfirmValid(java.lang.String)}.
	 */
	@Test
	public void testIsPasswordConfirmValid() {
		boolean positive = registrationUserInfoValidator.isPasswordValid(passwordValid);
		boolean negativePasswordWithoutPunct = registrationUserInfoValidator
				.isPasswordValid(passwordInvalidWithoutPunct);
		boolean negativePasswordWithoutDigit = registrationUserInfoValidator
				.isPasswordValid(passwordInvalidWithoutDigit);
		boolean negativePasswordWithoutAlpha = registrationUserInfoValidator
				.isPasswordValid(passwordInvalidWithoutAlpha);
		boolean negativePasswordLessSymbols = registrationUserInfoValidator.isPasswordValid(passwordInvalidLessSymbols);
		boolean negativePasswordMoreSymbols = registrationUserInfoValidator.isPasswordValid(passwordInvalidMoreSymbols);
		Assert.assertTrue(positive);
		Assert.assertFalse(negativePasswordWithoutPunct);
		Assert.assertFalse(negativePasswordWithoutDigit);
		Assert.assertFalse(negativePasswordWithoutAlpha);
		Assert.assertFalse(negativePasswordLessSymbols);
		Assert.assertFalse(negativePasswordMoreSymbols);
	}

}
