package by.petrovich.storage.controller.impl;

import java.io.IOException;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToCommandError implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServletException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PathToPage.COMMAND_ERROR);
		requestDispatcher.forward(request, response);
	}
}
