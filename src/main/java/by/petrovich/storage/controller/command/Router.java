package by.petrovich.storage.controller.command;

public class Router {
	public enum RouterType {
		FORWARD, REDIRECT, ERROR
	}

	private String pagePath;
	private final RouterType routerType;

	public Router(String pagePath, RouterType routerType) {
		this.pagePath = pagePath;
		this.routerType = routerType;
	}

	public String getPagePath() {
		return pagePath;
	}

	public RouterType getRouterType() {
		return routerType;
	}

}
