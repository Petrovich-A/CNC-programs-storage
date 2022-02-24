package by.petrovich.storage.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogOut implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("user");
			logger.info("user have deleted from session");
		}
		request.getSession(true);
		return new Router(PathToPage.MAIN, RouterType.FORWARD);
	}

}
