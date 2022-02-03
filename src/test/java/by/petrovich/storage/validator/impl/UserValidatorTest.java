package by.petrovich.storage.validator.impl;

import org.junit.Assert;
import org.junit.Test;

import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.User;

public class UserValidatorTest {

	@Test
	public void testIsUserValidWhenUserValidReturnTrue() {
		UserValidator userValidator = UserValidator.getInstance();
		User userActual = new User(65484, "efwef23!#", "Иван", "Иванов", "Рудольфович", EmployeePosition.CNC_PROGRAMMER,
				"ivanov@mail.ru");
		User userActual1 = new User(12345, "кпоулр!уаkh534", "John", "Smith", "Smitovich", EmployeePosition.ENGINEERING_TECHNICIAN,
				"america@usa.com");
		User userActual2 = new User(54218, "кпоу(efw:ef3234", "Слесарев", "Николай", "Иванович", EmployeePosition.CNC_PROGRAMMER,
				"bigBoss@gov.no");
		User userActualwithRole = new User(84576, "кпоswgv)wef234", "Ролевой", "Роман", "Иванович",
				EmployeePosition.CNC_PROGRAMMER, "withRole@sm.com");
		boolean actual = userValidator.isUserValid(userActual);
		boolean actual1 = userValidator.isUserValid(userActual1);
		boolean actual2 = userValidator.isUserValid(userActual2);
		boolean actualwithRole = userValidator.isUserValid(userActualwithRole);
		Assert.assertTrue("test", actual);
		Assert.assertTrue("test1", actual1);
		Assert.assertTrue("test2", actual2);
		Assert.assertTrue("testWithRole", actualwithRole);
	}
}