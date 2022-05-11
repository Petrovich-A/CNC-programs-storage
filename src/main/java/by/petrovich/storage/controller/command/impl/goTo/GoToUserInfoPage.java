package by.petrovich.storage.controller.command.impl.goTo;

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

public class GoToUserInfoPage implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final UserService userService = serviceProvider.getUserService();

	@Override
	public Router execute(HttpServletRequest request) {
		int personnelNumber = 0;
		User userFromDao = new User();
		HttpSession session = request.getSession(true);
		if (request.getParameter("personnelNumber") != null) {
			personnelNumber = Integer.parseInt(request.getParameter("personnelNumber"));
		}
		Optional<User> userOptional = Optional.empty();
		try {
			userOptional = userService.readUserByPersonnelNumber(personnelNumber);
			userFromDao = userOptional.get();
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "has no userFromDao: {}", userFromDao.toString(), e);
		}
		session.setAttribute("userFromDao", userFromDao);
		return new Router(PathToPage.USER_INFO, RouterType.FORWARD);
	}

}
