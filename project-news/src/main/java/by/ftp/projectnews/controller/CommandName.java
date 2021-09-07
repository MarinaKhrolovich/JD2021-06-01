package by.ftp.projectnews.controller;

public enum CommandName {
	REGISTRATION,
	AUTHORIZATION,
	REGISTRATION_NEW_USER,
	AUTHORIZATION_USER,
	GO_TO_MAIN_PAGE,
	GO_TO_USER_PAGE,
	UNKNOWN_COMMAND,
	CHANGE_LOCAL,
	ADD_NEWS,
	DELETE_NEWS,
	UPDATE_NEWS,
	GO_TO_PAGE_NEWS,
	GO_TO_UPDATE_NEWS_PAGE,
	GET_LAST_NEWSES,
	LOG_OUT;

	@Override
	public String toString() {

		return super.toString();
	}
	
}
