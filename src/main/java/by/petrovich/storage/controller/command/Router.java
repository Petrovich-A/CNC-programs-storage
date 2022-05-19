package by.petrovich.storage.controller.command;

/**
 * @author Petrovich A.V.
 *
 */
public class Router {
	public enum RouterType {
		FORWARD, REDIRECT, ERROR
	}

	private String pagePath;
	private final RouterType routerType;

	/**
	 * @param pagePath
	 * @param routerType
	 */
	public Router(String pagePath, RouterType routerType) {
		this.pagePath = pagePath;
		this.routerType = routerType;
	}

	/**
	 * @return
	 */
	public String getPagePath() {
		return pagePath;
	}

	/**
	 * @return
	 */
	public RouterType getRouterType() {
		return routerType;
	}

}
