package by.ftp.projectnews.service.validator;

public class ValidatorException extends Exception {

	private static final long serialVersionUID = -5582152432927044124L;

	public ValidatorException() {
		super();
	}

	public ValidatorException(String message) {
		super("ERROR: " + message);
	}

	public ValidatorException(Exception e) {
		super(e);
	}

	public ValidatorException(String message, Exception e) {
		super("ERROR: " + message, e);
	}

	@Override
	public String getMessage() {

		return super.getMessage();
	}

}
