package by.petrovich.storage.controller.impl;

import java.io.IOException;

import by.petrovich.storage.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServletException {
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		String path = (String) session.getAttribute("url");
		request.getRequestDispatcher(path).forward(request, response);

	}

}
