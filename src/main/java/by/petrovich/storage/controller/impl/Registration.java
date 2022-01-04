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
    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final UserService userService = serviceProvider.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = createUser(request);
        try {
            userService.registration(user);
            logger.log(Level.DEBUG, "user from UI is get", user.toString());
            request.getSession(true).setAttribute("user from UI", user);
            response.sendRedirect(PathToPage.LOG_IN);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, e.getLocalizedMessage());
        }
    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setLoginPersonnelNumber(Integer.parseInt(getParameterToCheck("loginPersonnelNumber", request)));
        user.setPassword(getParameterToCheck("password", request));
        user.setEmployeeName(getParameterToCheck("employeeName", request));
        user.setEmployeeSurname(getParameterToCheck("employeeSurname", request));
        user.setEmployeePatronymic(getParameterToCheck("employeePatronymic", request));
        user.setPosition(getParameterToCheck("position", request));
        user.setEmail(getParameterToCheck("email", request));
        user.setDate(new Date());
        user.setUserRole(UserRole.USER);
        return user;
    }

    private String getParameterToCheck(String name, HttpServletRequest request) {
        final String parameter = request.getParameter(name);
        if (parameter == null) {
            throw new IllegalArgumentException("request have no parameter with name: " + name);
        }
        return parameter;
    }
}
