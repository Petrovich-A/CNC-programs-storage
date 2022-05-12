/**
 * 
 */
package by.petrovich.storage.service.impl;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;

/**
 * @author Petrovich A.V.
 *
 */
public class UserServiceImplTest {
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();
	private String password = "wdf123wef*";
	private String passwordConfirm = "wdf123wef*";
	private String passwordNotMatch = "wdf458wef*";
	private String passwordNotValid = "wdfqdwdwef";
	private int login = 15000;
	private int loginNotValid = 842475;

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

}
