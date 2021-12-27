package by.petrovich.storage.entity;

public enum UserRole {
    GUEST(1),
    USER(2),
    ADMINISTRATOR(3);

    private int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static UserRole ofUserRole(int value) {
        return UserRole.ofUserRole(value);
    }

}
