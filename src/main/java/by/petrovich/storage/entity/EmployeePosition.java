package by.petrovich.storage.entity;

public enum EmployeePosition {
	ENGINEERING_TECHNICIAN("engineering_technician", 1),
	CNC_PROGRAMMER("cnc_programmer", 2);

	private String position;
	private int ordinalNumber;

	EmployeePosition(String positionName, int ordinalNumber) {
		this.position = positionName;
		this.ordinalNumber = ordinalNumber;
	}

	public int getOrdinalNumber() {
		return ordinalNumber;
	}

	public void setOrdinalNumber(int ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	public String getPosition() {
		return this.position;
	}

	public static EmployeePosition ofEmployeePosition(String value) {
		return EmployeePosition.ofEmployeePosition(value);
	}

	public static EmployeePosition fromString(String position) {
		for (EmployeePosition employeePosition : EmployeePosition.values()) {
			if (employeePosition.position.equalsIgnoreCase(position)) {
				return employeePosition;
			}
		}
		return null;
	}

}
