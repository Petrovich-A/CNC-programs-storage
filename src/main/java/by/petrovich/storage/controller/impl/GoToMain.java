package by.petrovich.storage.controller.impl;

import java.io.IOException;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMain implements Command {
//	private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.MAIN);
		requestDispatcher.forward(request, response);
	}

}
