package by.ftp.projectnews.dao;

public class DAOException extends Exception {

	public DAOException() {
		super();
	}


	public DAOException(String message) {
		super("Invalid password! Try again:"+message);

	}


	public DAOException(String message, Throwable cause) {
		super("Invalid password! Try again:"+message, cause);
	}


	public DAOException(Throwable cause) {
		super(cause);

	}


	

}
