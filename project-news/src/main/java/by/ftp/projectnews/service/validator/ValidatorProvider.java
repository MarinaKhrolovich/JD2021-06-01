package by.ftp.projectnews.service.validator;

public class ValidatorProvider {

	private static final ValidatorProvider instance = new ValidatorProvider();

	private final UserFullCheck userValidator = new UserFullCheck();
	private final NewsFullCheck newValidator = new NewsFullCheck();

	private ValidatorProvider() {
	}

	public static ValidatorProvider getInstance() {
		return instance;
	}

	public UserFullCheck getUserValidator() {
		return userValidator;
	}

	public NewsFullCheck getNewValidator() {
		return newValidator;
	}
	
}
