package by.petrovich.storage.validator;

import by.petrovich.storage.entity.User;

public interface UserValidatable {
    boolean isUserValid(User user);
}
