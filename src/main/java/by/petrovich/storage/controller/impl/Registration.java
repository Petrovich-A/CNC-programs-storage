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
import java.time.LocalDate;
import java.util.Date;

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final UserService USER_SERVICE = SERVICE_PROVIDER.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 1; // to do
        String loginPersonnelNumber = request.getParameter("loginPersonnelNumber");
        String password = request.getParameter("password");
        String employeeName = request.getParameter("employeeName");
        String employeeSurname = request.getParameter("employeeSurname");
        String employeePatronimic = request.getParameter("employeePatronimic");
        String position = request.getParameter("position");
        String email = request.getParameter("email");
        Date date = java.sql.Date.valueOf(LocalDate.now());
        UserRole userRole = UserRole.USER;
        User user = new User(id, Integer.parseInt(loginPersonnelNumber), password, employeeName, employeeSurname, employeePatronimic,
                position, email, date, userRole);

        if (user.equals(null)) {
            logger.log(Level.ERROR, "user from UI form is empty", user.toString());
        } else {
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
