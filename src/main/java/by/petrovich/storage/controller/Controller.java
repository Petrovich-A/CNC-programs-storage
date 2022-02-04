package by.petrovich.storage.controller;

import java.io.IOException;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.CommandProvider;
import by.petrovich.storage.controller.command.Router;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_REQUEST_PARAM = "commandName";
	private final CommandProvider commandProvider = new CommandProvider();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND_REQUEST_PARAM);
		Command command = commandProvider.findCommand(commandName);
		Router router = command.execute(request, response);
		switch (router.getRouterType()) {
		case FORWARD:
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPagePath());
			requestDispatcher.forward(request, response);
			break;

		case REDIRECT:
			response.sendRedirect(router.getPagePath());
			break;

		default:
			break;
		}
	}

}
