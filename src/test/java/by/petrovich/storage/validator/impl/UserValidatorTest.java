package by.petrovich.storage.validator.impl;

import by.petrovich.storage.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {

    @Test
    public void testIsUserValidWhenUserValidReturnTrue() {
        User userActual = new User(65484, "efwef23!#", "����", "������",
                "�����������", "�������-��������","ivanov@mail.ru");
        User userActual1 = new User(12345, "������!��kh534", "John", "Smith",
                "Smitovich", "���������", "america@usa.com");
        UserValidator userValidator = new UserValidator();
        boolean actual = userValidator.isUserValid(userActual);
        boolean actual1 = userValidator.isUserValid(userActual);
        Assert.assertTrue("test", actual);
        Assert.assertTrue("test1", actual1);
    }
}