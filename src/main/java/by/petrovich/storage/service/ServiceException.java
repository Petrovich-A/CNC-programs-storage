package by.petrovich.storage.service;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message = "message is empty";

	public ServiceException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public ServiceException(String message) {
		this.message = message;
	}

	public ServiceException(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
