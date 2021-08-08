package by.ftp.projectnews.dao.connectionpool;

public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String messager) {
		super(messager);
	}

	public ConnectionPoolException(String messager, Exception e) {
		super(messager, e);
	}
}
