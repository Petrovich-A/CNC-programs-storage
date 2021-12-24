package by.petrovich.storage.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.petrovich.storage.controller.Command;

public class GoToMain implements Command {
//	private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
//	private static final INewsService I_NEWS_SERVICE = SERVICE_PROVIDER.getNewsService();
	final static String PATH = "/WEB-INF/jsp/Main.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
		requestDispatcher.forward(request, response);
	}

}
