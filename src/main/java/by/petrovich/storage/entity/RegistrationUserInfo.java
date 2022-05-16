package by.petrovich.storage.entity;

import java.sql.Timestamp;
import java.util.Objects;

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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistrationUserInfo [personnelNumber=").append(personnelNumber).append(", employeeName=")
				.append(employeeName).append(", employeeSurname=").append(employeeSurname)
				.append(", employeePatronymic=").append(employeePatronymic).append(", employeePosition=")
				.append(employeePosition).append(", email=").append(email).append(", password=").append(password)
				.append(", confirmPassword=").append(confirmPassword).append(", userRole=").append(userRole)
				.append(", ÒreationDate=").append(ÒreationDate).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmPassword, email, employeeName, employeePatronymic, employeePosition, employeeSurname,
				password, personnelNumber, userRole, ÒreationDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationUserInfo other = (RegistrationUserInfo) obj;
		return Objects.equals(confirmPassword, other.confirmPassword) && Objects.equals(email, other.email)
				&& Objects.equals(employeeName, other.employeeName)
				&& Objects.equals(employeePatronymic, other.employeePatronymic)
				&& employeePosition == other.employeePosition && Objects.equals(employeeSurname, other.employeeSurname)
				&& Objects.equals(password, other.password) && personnelNumber == other.personnelNumber
				&& userRole == other.userRole && Objects.equals(ÒreationDate, other.ÒreationDate);
	}

	public static class RegistrationUserInfoBuilder {
		private RegistrationUserInfo newRegistrationUserInfo;

		public RegistrationUserInfoBuilder() {
			newRegistrationUserInfo = new RegistrationUserInfo();
		}

		/**
		 * @param personnelNumber the personnelNumber to set
		 */
		public RegistrationUserInfoBuilder withPersonnelNumber(int personnelNumber) {
			newRegistrationUserInfo.personnelNumber = personnelNumber;
			return this;
		}

		/**
		 * @param employeeName the employeeName to set
		 */
		public RegistrationUserInfoBuilder withEmployeeName(String employeeName) {
			newRegistrationUserInfo.employeeName = employeeName;
			return this;
		}

		/**
		 * @param employeeSurname the employeeSurname to set
		 */
		public RegistrationUserInfoBuilder withEmployeeSurname(String employeeSurname) {
			newRegistrationUserInfo.employeeSurname = employeeSurname;
			return this;
		}

		/**
		 * @param employeePatronymic the employeePatronymic to set
		 */
		public RegistrationUserInfoBuilder withEmployeePatronymic(String employeePatronymic) {
			newRegistrationUserInfo.employeePatronymic = employeePatronymic;
			return this;
		}

		/**
		 * @param employeePosition the employeePosition to set
		 */
		public RegistrationUserInfoBuilder withEmployeePosition(EmployeePosition employeePosition) {
			newRegistrationUserInfo.employeePosition = employeePosition;
			return this;
		}

		/**
		 * @param email the email to set
		 */
		public RegistrationUserInfoBuilder withEmail(String email) {
			newRegistrationUserInfo.email = email;
			return this;
		}

		/**
		 * @param password the password to set
		 */
		public RegistrationUserInfoBuilder withPassword(String password) {
			newRegistrationUserInfo.password = password;
			return this;
		}

		/**
		 * @param confirmPassword the confirmPassword to set
		 */
		public RegistrationUserInfoBuilder withConfirmPassword(String confirmPassword) {
			newRegistrationUserInfo.confirmPassword = confirmPassword;
			return this;
		}

		/**
		 * @param userRole the userRole to set
		 */
		public RegistrationUserInfoBuilder withUserRole(UserRole userRole) {
			newRegistrationUserInfo.userRole = userRole;
			return this;
		}

		/**
		 * @param ÒreationDate the ÒreationDate to set
		 */
		public RegistrationUserInfoBuilder with—reationDate(Timestamp ÒreationDate) {
			newRegistrationUserInfo.ÒreationDate = ÒreationDate;
			return this;
		}

		public RegistrationUserInfo build() {
			return newRegistrationUserInfo;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("RegistrationUserInfoBuilder [newRegistrationUserInfo=").append(newRegistrationUserInfo)
					.append("]");
			return builder.toString();
		}

	}

}
