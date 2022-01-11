package by.petrovich.storage.entity;

import java.util.Date;
import java.util.StringJoiner;

public class User {
    private int id;
    private int loginPersonnelNumber;
    private String password;
    private String employeeName;
    private String employeeSurname;
    private String employeePatronymic;
    private String position;
    private String email;
    private Date date;
    private UserRole userRole;

    public User() {
    }

    public User(int loginPersonnelNumber, String password, String employeeName, String employeeSurname,
                String employeePatronymic, String position, String email, Date date, UserRole userRole) {
        this.loginPersonnelNumber = loginPersonnelNumber;
        this.password = password;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeePatronymic = employeePatronymic;
        this.position = position;
        this.email = email;
        this.date = date;
        this.userRole = userRole;
    }

    public User(int id, int loginPersonnelNumber, String password, String employeeName, String employeeSurname,
                String employeePatronymic, String position, String email, Date date, UserRole userRole) {
        this.id = id;
        this.loginPersonnelNumber = loginPersonnelNumber;
        this.password = password;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeePatronymic = employeePatronymic;
        this.position = position;
        this.email = email;
        this.date = date;
        this.userRole = userRole;
    }

    public User(int loginPersonnelNumber, String password, String employeeName, String employeeSurname, String employeePatronymic, String position, String email) {
        this.loginPersonnelNumber = loginPersonnelNumber;
        this.password = password;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeePatronymic = employeePatronymic;
        this.position = position;
        this.email = email;
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

        if (getId() != user.getId()) return false;
        if (getLoginPersonnelNumber() != user.getLoginPersonnelNumber()) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getEmployeeName() != null ? !getEmployeeName().equals(user.getEmployeeName()) : user.getEmployeeName() != null)
            return false;
        if (getEmployeeSurname() != null ? !getEmployeeSurname().equals(user.getEmployeeSurname()) : user.getEmployeeSurname() != null)
            return false;
        if (getEmployeePatronymic() != null ? !getEmployeePatronymic().equals(user.getEmployeePatronymic()) : user.getEmployeePatronymic() != null)
            return false;
        if (getPosition() != null ? !getPosition().equals(user.getPosition()) : user.getPosition() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getDate() != null ? !getDate().equals(user.getDate()) : user.getDate() != null) return false;
        return getUserRole() == user.getUserRole();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getLoginPersonnelNumber();
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmployeeName() != null ? getEmployeeName().hashCode() : 0);
        result = 31 * result + (getEmployeeSurname() != null ? getEmployeeSurname().hashCode() : 0);
        result = 31 * result + (getEmployeePatronymic() != null ? getEmployeePatronymic().hashCode() : 0);
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getUserRole() != null ? getUserRole().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("loginPersonnelNumber=" + loginPersonnelNumber)
                .add("password='" + password + "'")
                .add("employeeName='" + employeeName + "'")
                .add("employeeSurname='" + employeeSurname + "'")
                .add("employeePatronymic='" + employeePatronymic + "'")
                .add("position='" + position + "'")
                .add("email='" + email + "'")
                .add("date=" + date)
                .add("userRole=" + userRole)
                .toString();
    }
}
