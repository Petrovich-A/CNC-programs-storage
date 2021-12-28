package by.petrovich.storage.controller.impl;

import java.io.IOException;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.REGISTRATION);
		requestDispatcher.forward(request, response);
	}

}
