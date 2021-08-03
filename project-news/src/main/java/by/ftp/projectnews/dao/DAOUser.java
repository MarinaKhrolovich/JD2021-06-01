package by.ftp.projectnews.dao;

import by.ftp.projectnews.bean.User;

public interface DAOUser {
	
	void registrationSQL(User user) throws DAOException;

	User authorizationSQL(String login) throws DAOException;
	
}
