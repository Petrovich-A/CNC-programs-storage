package by.petrovich.storage.entity;

public enum EmployeePosition {
	ENGINEERING_TECHNICIAN("engineering_technician", 1),
	CNC_PROGRAMMER("cnc_programmer", 2);

	private String positionName;
	private int ordinalNumber;

	EmployeePosition(String positionName, int ordinalNumber) {
		this.positionName = positionName;
		this.ordinalNumber = ordinalNumber;
	}

	public int getOrdinalNumber() {
		return ordinalNumber;
	}

	public void setOrdinalNumber(int ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public static EmployeePosition fromString(String positionName) {
		for (EmployeePosition employeePosition : EmployeePosition.values()) {
			if (employeePosition.positionName.equalsIgnoreCase(positionName)) {
				return employeePosition;
			}
		}
		return null;
	}

}
