package by.ftp.projectnews.service;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.service.validator.ValidatorException;

public interface UserService {

	void registration(RegistrationInfo regInfo) throws ServiceException, ValidatorException;

	User authorization(String login, String password) throws ServiceException;
	
	User getUser(String login) throws ServiceException;
	
	void delete(User user) throws ServiceException;

}
