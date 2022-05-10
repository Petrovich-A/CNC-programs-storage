package by.petrovich.storage.controller.entity;

import java.sql.Timestamp;
import java.util.Objects;

import by.petrovich.storage.entity.EmployeePosition;
import by.petrovich.storage.entity.UserRole;

public class RegistrationUserInfo {
	private int personnelNumber;
	private String employeeName;
	private String employeeSurname;
	private String employeePatronymic;
	private EmployeePosition employeePosition;
	private String email;
	private String password;
	private String confirmPassword;
	private UserRole userRole;
	private Timestamp ÒreationDate;

	/**
	 * 
	 */
	private RegistrationUserInfo() {
	}

	/**
	 * @return the personnelNumber
	 */
	public int getPersonnelNumber() {
		return personnelNumber;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @return the employeeSurname
	 */
	public String getEmployeeSurname() {
		return employeeSurname;
	}

	/**
	 * @return the employeePatronymic
	 */
	public String getEmployeePatronymic() {
		return employeePatronymic;
	}

	/**
	 * @return the employeePosition
	 */
	public EmployeePosition getEmployeePosition() {
		return employeePosition;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the ÒreationDate
	 */
	public Timestamp get—reationDate() {
		return ÒreationDate;
	}

	/**
	 * @param ÒreationDate the ÒreationDate to set
	 */
	public void set—reationDate(Timestamp ÒreationDate) {
		this.ÒreationDate = ÒreationDate;
	}

	public static class RegistrationUserInfoBuilder {
		private int personnelNumber;
		private String employeeName;
		private String employeeSurname;
		private String employeePatronymic;
		private EmployeePosition employeePosition;
		private String email;
		private String password;
		private String confirmPassword;
		private Timestamp timestamp;

		/**
		 * @param personnelNumber the loginPersonnelNumber to set
		 */
		public RegistrationUserInfoBuilder withPersonnelNumber(int personnelNumber) {
			this.personnelNumber = personnelNumber;
			return this;
		}

		/**
		 * @param employeeName the employeeName to set
		 */
		public RegistrationUserInfoBuilder withEmployeeName(String employeeName) {
			this.employeeName = employeeName;
			return this;
		}

		/**
		 * @param employeeSurname the employeeSurname to set
		 */
		public RegistrationUserInfoBuilder withEmployeeSurname(String employeeSurname) {
			this.employeeSurname = employeeSurname;
			return this;
		}

		/**
		 * @param employeePatronymic the employeePatronymic to set
		 */
		public RegistrationUserInfoBuilder withEmployeePatronymic(String employeePatronymic) {
			this.employeePatronymic = employeePatronymic;
			return this;
		}

		/**
		 * @param employeePosition the employeePosition to set
		 */
		public RegistrationUserInfoBuilder withEmployeePosition(EmployeePosition employeePosition) {
			this.employeePosition = employeePosition;
			return this;
		}

		/**
		 * @param email the email to set
		 */
		public RegistrationUserInfoBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		/**
		 * @param password the password to set
		 */
		public RegistrationUserInfoBuilder withPassword(String password) {
			this.password = password;
			return this;
		}

		/**
		 * @param confirmPassword the confirmPassword to set
		 */
		public RegistrationUserInfoBuilder withConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
			return this;
		}

		/**
		 * @param timestamp the timestamp to set
		 */
		public RegistrationUserInfoBuilder withTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public RegistrationUserInfo build() {
			RegistrationUserInfo registrationUserInfo = new RegistrationUserInfo();
			return registrationUserInfo;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("RegistrationUserInfoBuilder [personnelNumber=").append(personnelNumber)
					.append(", employeeName=").append(employeeName).append(", employeeSurname=").append(employeeSurname)
					.append(", employeePatronymic=").append(employeePatronymic).append(", employeePosition=")
					.append(employeePosition).append(", email=").append(email).append(", password=").append(password)
					.append(", confirmPassword=").append(confirmPassword).append(", timestamp=").append(timestamp)
					.append("]");
			return builder.toString();
		}

		@Override
		public int hashCode() {
			return Objects.hash(confirmPassword, email, employeeName, employeePatronymic, employeePosition,
					employeeSurname, password, personnelNumber, timestamp);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RegistrationUserInfoBuilder other = (RegistrationUserInfoBuilder) obj;
			return Objects.equals(confirmPassword, other.confirmPassword) && Objects.equals(email, other.email)
					&& Objects.equals(employeeName, other.employeeName)
					&& Objects.equals(employeePatronymic, other.employeePatronymic)
					&& employeePosition == other.employeePosition
					&& Objects.equals(employeeSurname, other.employeeSurname)
					&& Objects.equals(password, other.password) && personnelNumber == other.personnelNumber
					&& Objects.equals(timestamp, other.timestamp);
		}

	}

}
