package by.ftp.projectnews.service.impl;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOProvider;
import by.ftp.projectnews.dao.DAOUser;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.UserService;

public class UserServiceImpl implements UserService  {

	private static final DAOProvider daoProvider =DAOProvider.getInstance();
	private static final DAOUser DAOUser = daoProvider.getDaoUser();

	@Override
	public void registration(User user) throws ServiceException {
		
		try {
			DAOUser.registrationSQL(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public User authorization(String login, String password) throws ServiceException {

		//check parameters!
		if (login ==null||login.isEmpty()) {
			throw new ServiceException("Incorrect login!");
		}
		try {
		
			return DAOUser.authorizationSQL(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
