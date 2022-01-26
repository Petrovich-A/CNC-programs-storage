package by.petrovich.storage.entity;

public enum EmployeePosition {
	ENGINEERING_TECHNICIAN("engineering technologist"), CNC_PROGRAMMER("CNC programmer");

	private String position;

	EmployeePosition(String positionName) {
		this.position = positionName;
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
