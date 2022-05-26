package by.petrovich.storage.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
	private final int personnelNumber;
	private final String password;
	private final String employeeName;
	private final String employeeSurname;
	private final String employeePatronymic;
	private final EmployeePosition employeePosition;
	private final String email;
	private final Timestamp creationDate;
	private UserRole userRole;

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
	private User(Builder builder) {
		this.personnelNumber = builder.personnelNumber;
		this.password = builder.password;
		this.employeeName = builder.employeeName;
		this.employeeSurname = builder.employeeSurname;
		this.employeePatronymic = builder.employeePatronymic;
		this.employeePosition = builder.employeePosition;
		this.email = builder.email;
		this.creationDate = builder.creationDate;
		this.userRole = builder.userRole;
	}

	/**
	 * @return the personnelNumber
	 */
	public int getPersonnelNumber() {
		return personnelNumber;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
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
	 * @return the creationDate
	 */
	public Timestamp getCreationDate() {
		return creationDate;
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

	@Override
	public String toString() {
		return String.format(
				"User [personnelNumber=%s, password=%s, employeeName=%s, employeeSurname=%s, employeePatronymic=%s, employeePosition=%s, email=%s, creationDate=%s, userRole=%s]",
				personnelNumber, password, employeeName, employeeSurname, employeePatronymic, employeePosition, email,
				creationDate, userRole);
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationDate, email, employeeName, employeePatronymic, employeePosition, employeeSurname,
				password, personnelNumber, userRole);
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
				&& Objects.equals(employeePosition, other.employeePosition)
				&& Objects.equals(employeeSurname, other.employeeSurname) && Objects.equals(password, other.password)
				&& personnelNumber == other.personnelNumber && Objects.equals(userRole, other.userRole);
	}

	public static final class Builder {
		private int personnelNumber;
		private String password;
		private String employeeName;
		private String employeeSurname;
		private String employeePatronymic;
		private EmployeePosition employeePosition;
		private String email;
		private Timestamp creationDate;
		private UserRole userRole;

		/**
		 * @param personnelNumber the personnelNumber to with
		 */
		public Builder withPersonnelNumber(int personnelNumber) {
			this.personnelNumber = personnelNumber;
			return this;

		}

		/**
		 * @param password the password to with
		 */
		public Builder withPassword(String password) {
			this.password = password;
			return this;

		}

		/**
		 * @param employeeName the employeeName to with
		 */
		public Builder withEmployeeName(String employeeName) {
			this.employeeName = employeeName;
			return this;

		}

		/**
		 * @param employeeSurname the employeeSurname to with
		 */
		public Builder withEmployeeSurname(String employeeSurname) {
			this.employeeSurname = employeeSurname;
			return this;

		}

		/**
		 * @param employeePatronymic the employeePatronymic to with
		 */
		public Builder withEmployeePatronymic(String employeePatronymic) {
			this.employeePatronymic = employeePatronymic;
			return this;

		}

		/**
		 * @param email the email to with
		 */
		public Builder withEmail(String email) {
			this.email = email;
			return this;

		}

		/**
		 * @param employeePosition the employeePosition to
		 */
		public Builder withEmployeePosition(EmployeePosition employeePosition) {
			this.employeePosition = employeePosition;
			return this;
		}

		/**
		 * @param creationDate the creationDate to
		 */
		public Builder withCreationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		/**
		 * @param userRole the userRole to
		 */
		public Builder withUserRole(UserRole userRole) {
			this.userRole = userRole;
			return this;
		}

		/**
		 * @return
		 */
		public User build() {
			User user = new User(this);
			return user;
		}

	}
}
