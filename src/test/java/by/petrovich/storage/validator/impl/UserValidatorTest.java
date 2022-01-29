package by.petrovich.storage.validator.impl;

import by.petrovich.storage.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {

    @Test
    public void testIsUserValidWhenUserValidReturnTrue() {
        User userActual = new User(65484, "efwef23!#", "Иван", "Иванов",
                "Рудольфович", "инженер-технолог","ivanov@mail.ru");
        User userActual1 = new User(12345, "кпоулр!уаkh534", "John", "Smith",
                "Smitovich", "начальник", "america@usa.com");
        UserValidator userValidator = UserValidator.getInstance();
        boolean actual = userValidator.isUserValid(userActual);
        boolean actual1 = userValidator.isUserValid(userActual1);
        Assert.assertTrue("test", actual);
        Assert.assertTrue("test1", actual1);
    }
}