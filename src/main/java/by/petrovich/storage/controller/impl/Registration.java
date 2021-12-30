package by.petrovich.storage.controller.impl;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.entity.UserRole;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.ServletException;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginPersonnelNumber = request.getParameter("loginPersonnelNumber");
        String password = request.getParameter("password");
        String employeeName = request.getParameter("employeeName");
        String employeeSurname = request.getParameter("employeeSurname");
        String employeePatronimic = request.getParameter("employeePatronimic");
        String position = request.getParameter("position");
        String email = request.getParameter("email");
        User user = new User();
        if (loginPersonnelNumber == null || password == null || employeeName == null
                || employeeSurname == null || employeePatronimic == null || position == null || email == null) {
            logger.log(Level.DEBUG, "user from UI form is empty");
        } else {
            user.setLoginPersonnelNumber(Integer.parseInt(loginPersonnelNumber));
            user.setPassword(password);
            user.setEmployeeName(employeeName);
            user.setEmployeeSurname(employeeSurname);
            user.setEmployeePatronimic(employeePatronimic);
            user.setPosition(position);
            user.setEmail(email);
            user.setDate(new Date());
            user.setUserRole(UserRole.USER);
            try {
                USER_SERVICE.registration(user);
                logger.log(Level.DEBUG, "user from UI is get", user.toString());
                request.getSession(true).setAttribute("user from UI", user);
                response.sendRedirect(PathToPage.LOG_IN);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
