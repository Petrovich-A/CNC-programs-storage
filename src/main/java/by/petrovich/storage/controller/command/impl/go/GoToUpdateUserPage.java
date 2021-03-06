package by.petrovich.storage.controller.command.impl.go;

import static by.petrovich.storage.controller.command.SessionAttributeNames.PERSONNEL_NUMBER;
import static by.petrovich.storage.controller.command.SessionAttributeNames.USER_FOR_UPDATE;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.User;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import by.petrovich.storage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToUpdateUserPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		int personnelNumber = Integer.parseInt(request.getParameter("personnelNumber"));
		session.setAttribute(PERSONNEL_NUMBER, personnelNumber);
		Optional<User> userOptional = Optional.empty();
		try {
			userOptional = userService.readUserByPersonnelNumber(personnelNumber);
			if (userOptional.isPresent()) {
				User userForUpdate = userOptional.get();
				session.setAttribute(USER_FOR_UPDATE, userForUpdate);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User with personnelNumber: {} can't be read", personnelNumber, e);
		}
		return new Router(PathToPage.USER_UPDATE_PAGE, RouterType.FORWARD);
	}

}
