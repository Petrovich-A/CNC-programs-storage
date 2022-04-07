package by.petrovich.storage.controller.command.impl.goTo;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToErrorPage implements Command {

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		return new Router(PathToPage.ERROR, RouterType.FORWARD);
	}
}
