package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.SessionAttributeNames.LOCAL;
import static by.petrovich.storage.controller.command.SessionAttributeNames.PATH_TO_PAGE;

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
		session.setAttribute(LOCAL, request.getParameter("local"));
		return new Router((String) session.getAttribute(PATH_TO_PAGE), RouterType.FORWARD);
	}

}
