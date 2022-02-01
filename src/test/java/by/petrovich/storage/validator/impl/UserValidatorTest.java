package by.petrovich.storage.validator.impl;

import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;

import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {

	@Test
	public void testIsUserValidWhenUserValidReturnTrue() {
		UserValidator userValidator = UserValidator.getInstance();
		User userActual = new User(65484, "efwef23!#", "Иван", "Иванов", "Рудольфович", "инженер-технолог",
				"ivanov@mail.ru");
		User userActual1 = new User(12345, "кпоулр!уаkh534", "John", "Smith", "Smitovich", "наладчичк станков с ПУ",
				"america@usa.com");
		User userActual2 = new User(54218, "кпоу(efw:ef3234", "Слесарев", "Николай", "Иванович", "начальник",
				"bigBoss@gov.no");
		User userActualwithRole = new User(84576, "кпоswgv)wef234", "Ролевой", "Роман", "Иванович",
				"наладчичк станков с ПУ", "withRole@sm.com", UserRole.USER);
		boolean actual = userValidator.isUserValid(userActual);
		boolean actual1 = userValidator.isUserValid(userActual1);
		boolean actual2 = userValidator.isUserValid(userActual2);
		boolean actualwithRole = userValidator.isUserValid(userActualwithRole);
		Assert.assertTrue("test", actual);
		Assert.assertTrue("test1", actual1);
		Assert.assertFalse("test2", actual2);
		Assert.assertTrue("testWithRole", actualwithRole);
	}
}