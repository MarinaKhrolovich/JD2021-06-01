package by.ftp.projectnews.service;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -5582152432927044124L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super("ERROR: " + message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super("ERROR: " + message, e);
	}

	@Override
	public String getMessage() {

		return super.getMessage();
	}

}
