package by.petrovich.storage.hesher.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.hasher.impl.PasswordService;

public class PasswordHasherTest {
	private static final PasswordService service = PasswordService.getInstance();

	@Test
	public void testGenerateHashForPasswordString() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String password = "wefjh$87fw";
		String expected = "hI9KFZxB2JxkTIXpA//dq6L2Rjvmwrv4z+NAsvcEqkM=";
		String actual = service.generateHash(password);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGenerateHashForPasswordStringString() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String password = "edfkh11245%";
		String salt = "5hr8Uh32Hr";
		String expected = "l1HxeWB45urJ2czIiUOsQ9DOZqb7jPrzPjtBdUm6woQ=";
		String actual = service.generateHash(password, salt);
		Assert.assertEquals(expected, actual);
	}

}
