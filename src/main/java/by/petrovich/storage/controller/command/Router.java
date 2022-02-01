package by.petrovich.storage.controller.command;

public class Router {
	private String page = PathToPage.MAIN;
	private Router.RouterType routerType;

	public enum RouterType {
		FORWARD, REDIRECT;

		private RouterType() {
		}
	}

	public Router() {
		this.routerType = Router.RouterType.FORWARD;
	}

	public Router(String currentPage, RouterType forward) {
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		if (page == null) {
			page = PathToPage.ERROR;
		}
		this.page = page;
	}

	public Router.RouterType getType() {
		return this.routerType;
	}

	public void setRedirect() {
		this.routerType = Router.RouterType.REDIRECT;
	}

}
