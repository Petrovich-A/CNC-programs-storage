package by.petrovich.storage.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Petrovich A.V.
 *
 */
public enum UserRole {
	GUEST("guest", 1), USER("user", 2), ADMINISTRATOR("administrator", 3);

	private static final Logger logger = LogManager.getLogger();
	private int ordinalNumber;
	private String roleName;

	/**
	 * @param userRole
	 * @param ordinalNumber
	 */
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

	/**
	 * @param roleName
	 * @return
	 */
	public static UserRole fromString(String roleName) {
		for (UserRole userRole : UserRole.values()) {
			if (userRole.roleName.equalsIgnoreCase(roleName)) {
				return userRole;
			}
		}
		logger.log(Level.ERROR, "enum has no element as: {}", roleName);
		return null;
	}
}
