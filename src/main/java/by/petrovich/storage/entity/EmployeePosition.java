package by.petrovich.storage.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Petrovich A.V.
 *
 */
public enum EmployeePosition {
	ENGINEERING_TECHNICIAN("engineering_technician", 1), CNC_PROGRAMMER("cnc_programmer", 2);

	private static final Logger logger = LogManager.getLogger();
	private String positionName;
	private int ordinalNumber;

	/**
	 * @param positionName
	 * @param ordinalNumber
	 */
	EmployeePosition(String positionName, int ordinalNumber) {
		this.positionName = positionName;
		this.ordinalNumber = ordinalNumber;
	}

	/**
	 * @return
	 */
	public int getOrdinalNumber() {
		return ordinalNumber;
	}

	/**
	 * @param ordinalNumber
	 */
	public void setOrdinalNumber(int ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	/**
	 * @return
	 */
	public String getPositionName() {
		return this.positionName;
	}

	/**
	 * @param positionName
	 * @return
	 */
	public static EmployeePosition fromString(String positionName) {
		for (EmployeePosition employeePosition : EmployeePosition.values()) {
			if (employeePosition.positionName.equalsIgnoreCase(positionName)) {
				return employeePosition;
			}
		}
		logger.log(Level.ERROR, "enum has no element as: {}", positionName);
		return null;
	}

}
