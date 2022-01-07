package by.petrovich.storage.controller.command.impl;

import java.io.IOException;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocalCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		session.setAttribute("url", PathToPage.MAIN);
		String path = (String) session.getAttribute("url");
		request.getRequestDispatcher(path).forward(request, response);

	}

}
