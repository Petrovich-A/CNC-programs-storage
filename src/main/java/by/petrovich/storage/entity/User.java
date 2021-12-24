package by.petrovich.storage.entity;

import java.util.Date;
import java.util.StringJoiner;

public class User {
    private int id;
    private int loginPersonnelNumber;
    private String password;
    private String employeeSurname;
    private String employeePatronimic;
    private String position;
    private String email;
    private Date date;
    private UserRole userRole;

    public User(int id, int loginPersonnelNumber, String password, String employeeSurname, String employeePatronimic,
                String position, String email, Date date, UserRole userRole) {
        this.id = id;
        this.loginPersonnelNumber = loginPersonnelNumber;
        this.password = password;
        this.employeeSurname = employeeSurname;
        this.employeePatronimic = employeePatronimic;
        this.position = position;
        this.email = email;
        this.date = date;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getEmployeePatronimic() {
        return employeePatronimic;
    }

    public void setEmployeePatronimic(String employeePatronimic) {
        this.employeePatronimic = employeePatronimic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (loginPersonnelNumber != user.loginPersonnelNumber) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (employeeSurname != null ? !employeeSurname.equals(user.employeeSurname) : user.employeeSurname != null)
            return false;
        if (employeePatronimic != null ? !employeePatronimic.equals(user.employeePatronimic) : user.employeePatronimic != null)
            return false;
        if (position != null ? !position.equals(user.position) : user.position != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (date != null ? !date.equals(user.date) : user.date != null) return false;
        return userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + loginPersonnelNumber;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (employeeSurname != null ? employeeSurname.hashCode() : 0);
        result = 31 * result + (employeePatronimic != null ? employeePatronimic.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("loginPersonnelNumber=" + loginPersonnelNumber)
                .add("password='" + password + "'")
                .add("employeeSurname='" + employeeSurname + "'")
                .add("employeePatronimic='" + employeePatronimic + "'")
                .add("position='" + position + "'")
                .add("email='" + email + "'")
                .add("date=" + date)
                .add("userRole=" + userRole)
                .toString();
    }
}
