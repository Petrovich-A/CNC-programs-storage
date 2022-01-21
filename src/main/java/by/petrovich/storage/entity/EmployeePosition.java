package by.petrovich.storage.entity;

public enum EmployeePosition {
	ENGINEERING_TECHNICIAN("engineering_technician"), 
	CNC_PROGRAMMER("cnc_programmer");

	private String position;

	EmployeePosition(String positionName) {
		this.position = positionName;
	}

	public String getPosition() {
		return position;
	}

	public static EmployeePosition ofEmployeePosition(String value) {
		return EmployeePosition.ofEmployeePosition(value);
	}

}
