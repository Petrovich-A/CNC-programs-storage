package by.petrovich.storage.controller.command.impl;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AuthorizationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final UserService userService = serviceProvider.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginPersonnelNumber = getParameterToCheck("loginPersonnelNumber", request);
        String password = getParameterToCheck("password", request);
        HttpSession session = request.getSession(true);
        User userAuthorized = new User(Integer.parseInt(loginPersonnelNumber), password);
        try {
            userService.authorization(userAuthorized);
            session.setAttribute("userAuthorized", userAuthorized);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.MAIN);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.AUTHORIZATION);
            requestDispatcher.forward(request, response);
        }
    }

    private String getParameterToCheck(String name, HttpServletRequest request) {
        final String parameter = request.getParameter(name);
        if (parameter == null) {
            throw new IllegalArgumentException("request have no parameter with name: " + name);
        }
        return parameter;
    }
}
