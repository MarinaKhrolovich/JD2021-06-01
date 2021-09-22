package by.ftp.projectnews.dao;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;

public interface DAOUser {

	void registration(RegistrationInfo regInfo) throws DAOException;

	User authorization(String login, String password) throws DAOException;
	
	User getUser(String login) throws DAOException;
	
	void delete(User user) throws DAOException;

}
