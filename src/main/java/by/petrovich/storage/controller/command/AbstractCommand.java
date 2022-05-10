/**
 * 
 */
package by.petrovich.storage.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Router.RouterType;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Petrovich A.V.
 *
 */
public abstract class AbstractCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	protected String getParameterToCheck(String parameter, HttpServletRequest request) {
		final String parameterFromRequest = request.getParameter(parameter);
		if (parameterFromRequest == null || parameterFromRequest.equals("")) {
			logger.log(Level.ERROR, "Request contains parameter equals null: {}", parameterFromRequest);
			throw new IllegalArgumentException("Request contains parameter eaqual null: {}" + parameterFromRequest);
		}
		return parameterFromRequest;
	}

	protected Router createRouterWithAttribute(HttpServletRequest request, String pathToPage, String attributeName,
			Object attribute) {
		request.setAttribute(attributeName, attribute);
		return new Router(pathToPage, RouterType.FORWARD);
	}
}
