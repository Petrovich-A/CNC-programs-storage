package by.petrovich.storage.controller.command.impl;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToRegistrationPage implements Command {
	private final String FILL_THE_FORM = "Please fill the registration form below:";

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		session.setAttribute("registration_message", FILL_THE_FORM);
		return new Router(PathToPage.REGISTRATION, RouterType.FORWARD);
	}

}
