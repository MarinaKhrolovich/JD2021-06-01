package by.ftp.projectnews.service;

import by.ftp.projectnews.bean.User;

public interface UserService {

	void registration(User user) throws ServiceException; 
	User authorization(String login) throws ServiceException;

}
