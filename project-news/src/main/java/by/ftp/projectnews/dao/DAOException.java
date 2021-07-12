package by.ftp.projectnews.dao;

public class DAOException extends Exception {

	public DAOException() {
		super();
	}


	public DAOException(String message) {
		super("ERROR: "+message);

	}


	public DAOException(String message, Throwable cause) {
		super("ERROR: "+message, cause);
	}


	public DAOException(Throwable cause) {
		super(cause);

	}


	

}
