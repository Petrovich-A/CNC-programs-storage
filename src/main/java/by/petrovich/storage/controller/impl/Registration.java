package by.petrovich.storage.controller.impl;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Date;

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private final UserService USER_SERVICE = SERVICE_PROVIDER.getUserService();
    private String loginPersonnelNumber;
    private String password;
    private String employeeName;
    private String employeeSurname;
    private String employeePatronymic;
    private String position;
    private String email;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginPersonnelNumber = request.getParameter("loginPersonnelNumber");
        password = request.getParameter("password");
        employeeName = request.getParameter("employeeName");
        employeeSurname = request.getParameter("employeeSurname");
        employeePatronymic = request.getParameter("employeePatronimic");
        position = request.getParameter("position");
        email = request.getParameter("email");

        if (loginPersonnelNumber != null || password != null || employeeName != null
                || employeeSurname != null || employeePatronymic != null || position != null || email != null) {
            User user = buildUser(loginPersonnelNumber, password, employeeName, employeeSurname, employeePatronymic, position, email);
            try {
                USER_SERVICE.registration(user);
                logger.log(Level.DEBUG, "user from UI is get", user.toString());
                request.getSession(true).setAttribute("user from UI", user);
                response.sendRedirect(PathToPage.LOG_IN);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            logger.log(Level.DEBUG, "user from UI form is empty");
        }
    }

    private User buildUser(String loginPersonnelNumber, String password, String employeeName, String employeeSurname,
                           String employeePatronimic, String position, String email) {
        User user = new User();
        user.setLoginPersonnelNumber(Integer.parseInt(loginPersonnelNumber));
        user.setPassword(password);
        user.setEmployeeName(employeeName);
        user.setEmployeeSurname(employeeSurname);
        user.setEmployeePatronymic(employeePatronimic);
        user.setPosition(position);
        user.setEmail(email);
        user.setDate(new Date());
        user.setUserRole(UserRole.USER);
        return user;
    }
}
