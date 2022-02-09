package by.petrovich.storage.entity;

public enum UserRole {
	GUEST("guest", 1),
	USER("user", 2),
	ADMINISTRATOR("administrator", 3);

	private int ordinalNumber;
	private String roleName;

	UserRole(String userRole, int ordinalNumber) {
		this.roleName = userRole;
		this.ordinalNumber = ordinalNumber;
	}

	public void setOrdinalNumber(int ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	public int getOrdinalNumber() {
		return this.ordinalNumber;
	}

	public String getUserRole() {
		return this.roleName;
	}

	public static UserRole fromString(String roleName) {
		for (UserRole userRole : UserRole.values()) {
			if (userRole.roleName.equalsIgnoreCase(roleName)) {
				return userRole;
			}
		}
		return null;
	}
}
