package by.petrovich.storage.controller.command.impl.go;

import static by.petrovich.storage.controller.command.RequestAttributeNames.REGISTRATION_MESSAGE;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;

public class GoToRegistrationPage implements Command {
	private final String FILL_THE_FORM = "Please fill the registration form below:";

	@Override
	public Router execute(HttpServletRequest request) {
		request.setAttribute(REGISTRATION_MESSAGE, FILL_THE_FORM);
		return new Router(PathToPage.REGISTRATION_PAGE, RouterType.FORWARD);
	}

}
