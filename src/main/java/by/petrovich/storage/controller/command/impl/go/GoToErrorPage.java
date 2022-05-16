package by.petrovich.storage.controller.command.impl.go;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;

public class GoToErrorPage implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		return new Router(PathToPage.ERROR, RouterType.FORWARD);
	}
}
