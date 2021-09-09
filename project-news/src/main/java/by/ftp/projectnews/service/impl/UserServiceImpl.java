package by.ftp.projectnews.service.impl;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOProvider;
import by.ftp.projectnews.dao.DAOUser;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.UserService;

public class UserServiceImpl implements UserService {

	private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
	private static final DAOUser DAOUser = DAO_PROVIDER.getDaoUser();

	@Override
	public void registration(RegistrationInfo regInfo) throws ServiceException {

		try {
			DAOUser.registration(regInfo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User authorization(String login, String password) throws ServiceException {

		// check parameters!

		try {
			User user = DAOUser.authorization(login, password);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
