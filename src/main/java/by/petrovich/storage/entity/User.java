package by.petrovich.storage.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
	private int personnelNumber;
	private String password;
	private String employeeName;
	private String employeeSurname;
	private String employeePatronymic;
	private EmployeePosition employeePosition;
	private String email;
	private Timestamp creationDate;
	private UserRole userRole;

	public User() {
	}

	public User(int personnelNumber, String password) {
		super();
		this.personnelNumber = personnelNumber;
		this.password = password;
	}

	public User(int personnelNumber, String password, String employeeName, String employeeSurname,
			String employeePatronymic, EmployeePosition employeePosition, String email) {
		super();
		this.personnelNumber = personnelNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeePatronymic = employeePatronymic;
		this.employeePosition = employeePosition;
		this.email = email;
	}

	public User(int personnelNumber, String password, String employeeName, String employeeSurname,
			String employeePatronymic, EmployeePosition employeePosition, String email, Timestamp creationDate) {
		this.personnelNumber = personnelNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeePatronymic = employeePatronymic;
		this.employeePosition = employeePosition;
		this.email = email;
		this.creationDate = creationDate;
	}

	/**
	 * @param personnelNumber
	 * @param password
	 * @param employeeName
	 * @param employeeSurname
	 * @param employeePatronymic
	 * @param employeePosition
	 * @param email
	 * @param creationDate
	 * @param userRole
	 */
	public User(int personnelNumber, String password, String employeeName, String employeeSurname,
			String employeePatronymic, EmployeePosition employeePosition, String email, Timestamp creationDate,
			UserRole userRole) {
		super();
		this.personnelNumber = personnelNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeePatronymic = employeePatronymic;
		this.employeePosition = employeePosition;
		this.email = email;
		this.creationDate = creationDate;
		this.userRole = userRole;
	}

	public int getPersonnelNumber() {
		return personnelNumber;
	}

	public void setPersonnelNumber(int personnelNumber) {
		this.personnelNumber = personnelNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSurname() {
		return employeeSurname;
	}

	public void setEmployeeSurname(String employeeSurname) {
		this.employeeSurname = employeeSurname;
	}

	public String getEmployeePatronymic() {
		return employeePatronymic;
	}

	public void setEmployeePatronymic(String employeePatronymic) {
		this.employeePatronymic = employeePatronymic;
	}

	public EmployeePosition getEmployeePosition() {
		return employeePosition;
	}

	public void setEmployeePosition(EmployeePosition employeePosition) {
		this.employeePosition = employeePosition;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return personnelNumber == user.personnelNumber && Objects.equals(password, user.password)
				&& Objects.equals(employeeName, user.employeeName)
				&& Objects.equals(employeeSurname, user.employeeSurname)
				&& Objects.equals(employeePatronymic, user.employeePatronymic)
				&& employeePosition == user.employeePosition && Objects.equals(email, user.email)
				&& Objects.equals(creationDate, user.creationDate) && userRole == user.userRole;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personnelNumber, password, employeeName, employeeSurname, employeePatronymic,
				employeePosition, email, creationDate, userRole);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [personnelNumber=").append(personnelNumber).append(", password=").append(password)
				.append(", employeeName=").append(employeeName).append(", employeeSurname=").append(employeeSurname)
				.append(", employeePatronymic=").append(employeePatronymic).append(", employeePosition=")
				.append(employeePosition).append(", email=").append(email).append(", creationDate=")
				.append(creationDate).append(", userRole=").append(userRole).append("]");
		return builder.toString();
	}

}
