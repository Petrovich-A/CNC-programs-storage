package by.petrovich.storage.entity;

import java.util.StringJoiner;

public class RegistrationUserInfo {
	private int loginPersonnelNumber;
	private String employeeName;
	private String employeeSurname;
	private String employeePatronymic;
	private EmployeePosition employeePosition;
	private String email;
	private String password;
	private String confirmPassword;

	private RegistrationUserInfo() {
	}

	public int getLoginPersonnelNumber() {
		return loginPersonnelNumber;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getEmployeeSurname() {
		return employeeSurname;
	}

	public String getEmployeePatronymic() {
		return employeePatronymic;
	}

	public EmployeePosition getEmployeePosition() {
		return employeePosition;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", RegistrationUserInfo.class.getSimpleName() + "[", "]")
				.add("loginPersonnelNumber=" + loginPersonnelNumber).add("employeeName='" + employeeName + "'")
				.add("employeeSurname='" + employeeSurname + "'").add("employeePatronymic='" + employeePatronymic + "'")
				.add("employeePosition=" + employeePosition).add("email='" + email + "'")
				.add("password='" + password + "'").add("confirmPassword='" + confirmPassword + "'").toString();
	}

	public static Builder newBuilder() {
		return new RegistrationUserInfo().new Builder();
	}

	public class Builder {

		private Builder() {
		}

		public Builder setLoginPersonnelNumber(int loginPersonnelNumber) {
			RegistrationUserInfo.this.loginPersonnelNumber = loginPersonnelNumber;
			return this;
		}

		public Builder setEmployeeName(String employeeName) {
			RegistrationUserInfo.this.employeeName = employeeName;
			return this;

		}

		public Builder setEmployeeSurname(String employeeSurname) {
			RegistrationUserInfo.this.employeeSurname = employeeSurname;
			return this;

		}

		public Builder setEmployeePatronymic(String employeePatronymic) {
			RegistrationUserInfo.this.employeePatronymic = employeePatronymic;
			return this;

		}

		public Builder setEmployeePosition(EmployeePosition employeePosition) {
			RegistrationUserInfo.this.employeePosition = employeePosition;
			return this;

		}

		public Builder setEmail(String email) {
			RegistrationUserInfo.this.email = email;
			return this;

		}

		public Builder setPassword(String password) {
			RegistrationUserInfo.this.password = password;
			return this;

		}

		public Builder setConfirmPassword(String confirmPassword) {
			RegistrationUserInfo.this.confirmPassword = confirmPassword;
			return this;

		}

		public RegistrationUserInfo build() {
			return RegistrationUserInfo.this;
		}
	}

}
