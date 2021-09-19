package by.ftp.projectnews.controller.message;

public interface MessageLocal {

	public static final String USER_REG_SUCCESS = "local.message.user.regSuccess";
	public static final String USER_REG_EXISTS = "local.message.user.regExists";
	public static final String USER_AUTHO_INVALID_LOGIN = "local.message.user.authoInvalidLogin";
	public static final String USER_AUTHO_SUCCESS = "local.message.user.authoSuccess";
	public static final String USER_AUTO_LOGIN_WRONG = "local.message.user.autologinWrong";
	public static final String USER_AUTO_WRONG = "local.message.user.authoWrong";

	public static final String SESSION_LOST = "local.message.sessionLost";
	public static final String MUST_SIGN_IN = "local.message.mustSignIn";
	public static final String MUST_SIGN_IN_AS_ADMIN = "local.message.mustSignAsAdmin";
	public static final String FILL_ALL_FIELDS = "local.message.fillAllFields";
	public static final String FILL_FIELDS = "local.message.fillFields";
	public static final String UNKNOWN_COMMAND = "local.message.unknownCommand";

	public static final String NEWS_ADD_SUCCESS = "local.message.news.addSuccess";
	public static final String NEWS_ADD_TITLE_EXISTS = "local.message.news.addTitleExists";
	public static final String NEWS_ADD_WRONG = "local.message.news.addWrong";
	public static final String NEWS_DELETE_SUCCESS = "local.message.news.deleteSuccess";
	public static final String NEWS_DELETE_WRONG = "local.message.news.deleteWrong";
	public static final String NEWS_GET_WRONG = "local.message.news.getWrong";
	public static final String NEWS_UPDATE_SUCCESS = "local.message.news.updateSuccess";
	public static final String NEWS_UPDATE_WRONG = "local.message.news.updateWrong";
	public static final String NEWS_INCORRECT_ID = "local.message.news.incorrectId";
	public static final String NEWS_INCORRECT_TITLE = "local.message.news.incorrectTitle";
}
