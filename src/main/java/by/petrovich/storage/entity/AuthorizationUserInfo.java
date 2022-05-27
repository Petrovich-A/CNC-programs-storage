/**
 * 
 */
package by.petrovich.storage.entity;

import java.util.Objects;

/**
 * @author Petrovich A.V.
 *
 */
public class AuthorizationUserInfo {
	int login;
	String password;

	/**
	 * @param login
	 * @param password
	 */
	public AuthorizationUserInfo(int login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	/**
	 * 
	 */
	public AuthorizationUserInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the login
	 */
	public int getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(int login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthorizationUserInfo [login=");
		builder.append(login);
		builder.append(", ");
		if (password != null) {
			builder.append("password=");
			builder.append(password);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorizationUserInfo other = (AuthorizationUserInfo) obj;
		return login == other.login && Objects.equals(password, other.password);
	}

}
