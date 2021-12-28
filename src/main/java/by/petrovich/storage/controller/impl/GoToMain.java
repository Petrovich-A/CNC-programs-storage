package by.petrovich.storage.controller.impl;

import java.io.IOException;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMain implements Command {
//	private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.MAIN);
		requestDispatcher.forward(request, response);
	}

}
