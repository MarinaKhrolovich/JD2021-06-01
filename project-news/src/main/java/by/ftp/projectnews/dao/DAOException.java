package by.ftp.projectnews.dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = -5582152432927044124L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super("ERROR: " + message);
	}

	public DAOException(String message, Throwable cause) {
		super("ERROR: " + message, cause);
	}

	public DAOException(Throwable cause) {
		super(cause);

	}

}
