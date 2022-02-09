package by.petrovich.storage.entity;

public enum UserRole {
	GUEST(1), USER(2), ADMINISTRATOR(3);

	private int ordinalNumber;

	UserRole(int ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	public int getOrdinalNumber() {
		return this.ordinalNumber;
	}

	public static UserRole ofUserRole(int ordinalNumber) {
		return UserRole.ofUserRole(ordinalNumber);
	}

	public static UserRole fromString(String value) {
		return UserRole.valueOf(value);
	}

}
