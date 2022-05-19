package by.petrovich.storage.controller.command;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Petrovich A.V.
 *
 */
public interface Command {
	/**
	 * @param request
	 * @return
	 */
	Router execute(HttpServletRequest request);

}
