package by.ftp.projectnews.service;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;

public interface UserService {

	void registration(RegistrationInfo regInfo) throws ServiceException;

	User authorization(String login, String password) throws ServiceException;

}
