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

	/**
	 * The method returns a router type which contains the redirect type and the
	 * path to page as a string. Also, an attribute setting to the request plus an
	 * object type instance is setting.
	 * 
	 * @param request       Http Servlet Request
	 * @param pathToPage    is an URL as string to requested JSP page
	 * @param attributeName name of setting attribute as string
	 * @param attribute     an object type instance
	 * @return an router type object
	 */
	protected Router createRouterWithAttribute(HttpServletRequest request, String pathToPage, String attributeName,
			Object attribute) {
		request.setAttribute(attributeName, attribute);
		return new Router(pathToPage, RouterType.FORWARD);
	}

	/**
	 * The method checks if the parameter coming from the request is null
	 * 
	 * @param parameter String parameter from request
	 * @param request   Http Servlet Request
	 * @return String type parameter coming from request
	 */
	protected String getParameterToCheck(String parameter, HttpServletRequest request) {
		final String parameterFromRequest = request.getParameter(parameter);
		if (parameterFromRequest == null || parameterFromRequest.equals("")) {
			logger.log(Level.ERROR, "Request contains parameter equals null: {}", parameterFromRequest);
			throw new IllegalArgumentException("Request contains parameter eaqual null: {}" + parameterFromRequest);
		}
		return parameterFromRequest;
	}

}
