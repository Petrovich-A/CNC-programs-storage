/**
 * 
 */
package by.petrovich.storage.hasher.impl;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Petrovich A.V.
 *
 */
public class PasswordServiceTest {
	private static final PasswordService service = PasswordService.getInstance();

	/**
	 * Test method for
	 * {@link by.petrovich.storage.hasher.impl.PasswordService#generateSalt()}.
	 */
	@Test
	public void testGenerateSalt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.hasher.impl.PasswordService#generateHash(java.lang.String)}.
	 */
	@Test
	public void testGenerateHashString() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String password = "wefjh$87fw";
		String expected = "hI9KFZxB2JxkTIXpA//dq6L2Rjvmwrv4z+NAsvcEqkM=";
		String actual = service.generateHash(password);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link by.petrovich.storage.hasher.impl.PasswordService#generateHash(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGenerateHashStringString() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String password = "edfkh11245%";
		String salt = "5hr8Uh32Hr";
		String expected = "l1HxeWB45urJ2czIiUOsQ9DOZqb7jPrzPjtBdUm6woQ=";
		String actual = service.generateHash(password, salt);
		Assert.assertEquals(expected, actual);
	}

}
