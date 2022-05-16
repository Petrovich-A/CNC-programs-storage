/**
 * 
 */
package by.petrovich.storage.service.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.controller.entity.RegistrationUserInfo;
import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import by.petrovich.storage.validator.impl.RegistrationUserInfoValidator;

/**
 * @author Petrovich A.V.
 *
 */
public class UserServiceImplTest {
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private final RegistrationUserInfoValidator registrationUserInfoValidator = RegistrationUserInfoValidator
			.getInstance();
	private String password = "wdf123wef*";
	private String passwordConfirm = "wdf123wef*";
	private String passwordNotMatch = "wdf458wef*";
	private String passwordNotValid = "wdfqdwdwef";
	private int login = 15000;
	private int loginNotValid = 842475;
	private User user = new User(11000, "U/m9XgBquMvtjkFj3Nv3kJCp/EQML2cEp7+psQeMyR8=", "John", "Smith", "Ivanovich",
			EmployeePosition.ENGINEERING_TECHNICIAN, "john123@mail.com", Timestamp.valueOf("2022-02-25 08:38:30"),
			UserRole.USER);
	private User user2 = new User(11000, "U/m9XgBquMvtjkFj3Nv3kJCp/EQML2cEp7+psQeMyR8=", "John", "Smith", "Ivanovich",
			EmployeePosition.ENGINEERING_TECHNICIAN, "john123@mail.com", Timestamp.valueOf("2022-02-25 08:38:30"),
			UserRole.GUEST);
	private List<User> allusers = new ArrayList<>();
	private RegistrationUserInfo registrationUserInfo = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(42001).build();
	private RegistrationUserInfo registrationUserInfoValid = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(12000).withEmployeeName("John").withEmployeeSurname("Smith")
			.withEmployeePatronymic("Smitovich").withEmployeePosition(EmployeePosition.ENGINEERING_TECHNICIAN)
			.withEmail("america@usa.com").withPassword("nmverg24#!9").withConfirmPassword("nmverg24#!9").build();
	private RegistrationUserInfo registrationUserInfoInvalid = new RegistrationUserInfo.RegistrationUserInfoBuilder()
			.withPersonnelNumber(12000).withEmployeeName("John").withEmployeeSurname("Smith")
			.withEmployeePatronymic("Smitovich").withEmployeePosition(EmployeePosition.ENGINEERING_TECHNICIAN)
			.withEmail("america@usa.com").withPassword("nmverg24").withConfirmPassword("nmverg24").build();

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#readAllUsers()}.
	 */
	@Test
	public void readAllUsers() throws ServiceException {
//		allusers.add(user);
//		allusers.add(user2);
//		List<User> expected = allusers;
//		List<User> actual = userService.readAllUsers();
//		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isPasswordsMatch(java.lang.String, java.lang.String)}.
	 * 
	 * @throws ServiceException
	 */
	@Test
	public void isPasswordsMatchWhenPasswordsMatchReturnTrue() throws ServiceException {
		boolean actual = userService.isPasswordsMatch(password, passwordConfirm);
		boolean actual2 = userService.isPasswordsMatch(password, passwordNotMatch);
		Assert.assertTrue("Passwords are not the same.", actual);
		Assert.assertFalse("Passwords are different.", actual2);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isLoginAndPasswordMatchWithDateBaseData(int, java.lang.String)}.
	 * 
	 * @throws ServiceException
	 */
	@Test
	public void isLoginAndPasswordMatchWithDateBaseDataWhenMatchReturnTrue() throws ServiceException {
		boolean actual = userService.isLoginAndPasswordMatchWithDateBaseData(login, password);
		Assert.assertTrue("Password and login are not the same.", actual);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isLoginAndPasswordValid(int, java.lang.String)}.
	 * 
	 * @throws ServiceException
	 */
	@Test
	public void isLoginAndPasswordValidWhenLoginAndPasswordValidReturnTrue() throws ServiceException {
		boolean actual = userService.isLoginAndPasswordValid(login, password);
		boolean actual1 = userService.isLoginAndPasswordValid(loginNotValid, password);
		boolean actual2 = userService.isLoginAndPasswordValid(login, passwordNotValid);
		boolean actual3 = userService.isLoginAndPasswordValid(loginNotValid, passwordNotValid);
		Assert.assertTrue("Password and login are not valid.", actual);
		Assert.assertFalse("Somethig wrong.", actual1);
		Assert.assertFalse("Somethig wrong.", actual2);
		Assert.assertFalse("Somethig wrong.", actual3);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#readUserByPersonnelNumber(int)}.
	 */
	@Test
	public void readUserByPersonnelNumber() throws ServiceException {
		User expected = user;
		Optional<User> optional = userService.readUserByPersonnelNumber(11000);
		User actual = optional.get();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#authorizateUser(int, java.lang.String)}.
	 */
	@Test
	public void authorizateUser() throws ServiceException {
		User expected = user;
		Optional<User> optional = userService.authorizateUser(11000, "ghbdtn00!");
		User actual = optional.get();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#registrateUser(by.petrovich.storage.controller.entity.RegistrationUserInfo)}.
	 */
	@Test
	public void testRegistrateUser() throws ServiceException {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#logOut(by.petrovich.storage.entity.User)}.
	 */
	@Test
	public void testLogOut() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#update(by.petrovich.storage.entity.User, int)}.
	 */
	@Test
	public void testUpdate() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isValid(by.petrovich.storage.controller.entity.RegistrationUserInfo)}.
	 */
	@Test
	public void isValidReturnTrueWhenRegistrationUserInfoValid() throws ServiceException {
		boolean actual = userService.isValid(registrationUserInfoValid);
		boolean actual2 = userService.isValid(registrationUserInfoInvalid);
		Assert.assertTrue(actual);
		Assert.assertFalse(actual2);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.service.impl.UserServiceImpl#isUserExist(int)}.
	 */
	@Test
	public void isUserExistReturnTrueWhenUserExist() throws ServiceException {
		boolean actual = userService.isUserExist(registrationUserInfo.getPersonnelNumber());
		Assert.assertTrue(actual);
	}

}
