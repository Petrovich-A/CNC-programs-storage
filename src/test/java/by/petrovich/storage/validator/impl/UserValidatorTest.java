package by.petrovich.storage.validator.impl;

import by.petrovich.storage.entity.User;
import junit.framework.TestCase;
import org.junit.Test;

public class UserValidatorTest extends TestCase {

    @Test
    public void testIsUserValidWhenUserValidReturnTrue() {
        User userActual = new User(65484, "efwef23", "Иван", "Иванов",
                "Рудольфович", "инженер", "ivanov@mail.ru");
        UserValidator userValidator = new UserValidator();
        boolean isUserValid = userValidator.isUserValid(userActual);
        assertTrue("isUserValid: ", isUserValid);
    }
}