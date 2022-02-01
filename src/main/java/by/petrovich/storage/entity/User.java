package by.petrovich.storage.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
	private int loginPersonnelNumber;
	private String password;
	private String employeeName;
	private String employeeSurname;
	private String employeePatronymic;
	private EmployeePosition employeePosition;
	private String position;
	private String email;
	private Timestamp creationDate;
	private UserRole userRole;

	public User() {
	}

	public User(int loginPersonnelNumber, String password, String employeeName, String employeeSurname,
			String employeePatronymic, EmployeePosition employeePosition, String email, Timestamp creationDate,
			UserRole userRole) {
		super();
		this.loginPersonnelNumber = loginPersonnelNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeePatronymic = employeePatronymic;
		this.employeePosition = employeePosition;
		this.email = email;
		this.creationDate = creationDate;
		this.userRole = userRole;
	}

	public User(int loginPersonnelNumber, String password, String employeeName, String employeeSurname,
			String employeePatronymic, String position, String email, UserRole userRole) {
		super();
		this.loginPersonnelNumber = loginPersonnelNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeePatronymic = employeePatronymic;
		this.position = position;
		this.email = email;
		this.userRole = userRole;
	}

	public User(int loginPersonnelNumber, String password, String employeeName, String employeeSurname,
			String employeePatronymic, String position, String email) {
		super();
		this.loginPersonnelNumber = loginPersonnelNumber;
		this.password = password;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeePatronymic = employeePatronymic;
		this.position = position;
		this.email = email;
	}

	public User(int loginPersonnelNumber, String password) {
		super();
		this.loginPersonnelNumber = loginPersonnelNumber;
		this.password = password;
	}

	public int getLoginPersonnelNumber() {
		return loginPersonnelNumber;
	}

	public void setLoginPersonnelNumber(int loginPersonnelNumber) {
		this.loginPersonnelNumber = loginPersonnelNumber;
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

	public Timestamp getTimestamp() {
		return creationDate;
	}

	public void setTimestamp(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationDate, email, employeeName, employeePatronymic, employeePosition, employeeSurname,
				loginPersonnelNumber, password, userRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(email, other.email)
				&& Objects.equals(employeeName, other.employeeName)
				&& Objects.equals(employeePatronymic, other.employeePatronymic)
				&& employeePosition == other.employeePosition && Objects.equals(employeeSurname, other.employeeSurname)
				&& loginPersonnelNumber == other.loginPersonnelNumber && Objects.equals(password, other.password)
				&& userRole == other.userRole;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [loginPersonnelNumber=");
		builder.append(loginPersonnelNumber);
		builder.append(", password=");
		builder.append(password);
		builder.append(", employeeName=");
		builder.append(employeeName);
		builder.append(", employeeSurname=");
		builder.append(employeeSurname);
		builder.append(", employeePatronymic=");
		builder.append(employeePatronymic);
		builder.append(", employeePosition=");
		builder.append(employeePosition);
		builder.append(", email=");
		builder.append(email);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", userRole=");
		builder.append(userRole);
		builder.append("]");
		return builder.toString();
	}

}
