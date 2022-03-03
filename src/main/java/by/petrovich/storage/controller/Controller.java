package by.petrovich.storage.controller;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.CommandProvider;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger();
	private static final String COMMAND_REQUEST_PARAM = "commandName";
	private static final String COMMAND_REQUEST_ERROR = "Can't find required page.";
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
			logger.log(Level.ERROR, "Can't find required page.");
			request.setAttribute("error_message", COMMAND_REQUEST_ERROR);
			request.getRequestDispatcher(PathToPage.ERROR).forward(request, response);
		}
	}

}
