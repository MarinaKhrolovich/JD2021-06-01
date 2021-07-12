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
			e.printStackTrace();
		}
	}
	
	@Override
	public User authorization(String login, String password) throws ServiceException {

		try {
			return DAOUser.authorizationSQL(login, password);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
