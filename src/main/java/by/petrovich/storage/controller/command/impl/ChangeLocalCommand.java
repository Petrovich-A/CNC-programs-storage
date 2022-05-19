package by.petrovich.storage.controller.command.impl;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @author Petrovich A.V.
 *
 */
public class ChangeLocalCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		return new Router((String) session.getAttribute("path_to_page"), RouterType.FORWARD);
	}

}
