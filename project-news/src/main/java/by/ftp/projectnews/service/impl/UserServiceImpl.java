package by.ftp.projectnews.service.impl;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOProvider;
import by.ftp.projectnews.dao.DAOUser;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.UserService;
import by.ftp.projectnews.service.validator.UserFullCheck;
import by.ftp.projectnews.service.validator.ValidatorException;
import by.ftp.projectnews.service.validator.ValidatorProvider;

public class UserServiceImpl implements UserService {

	private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
	private static final DAOUser DAOUser = DAO_PROVIDER.getDaoUser();

	private static final ValidatorProvider VALIDATOR_PROVIDER = ValidatorProvider.getInstance();
	private static final   UserFullCheck userValidator = VALIDATOR_PROVIDER.getUserValidator();
	
	@Override
	public void registration(RegistrationInfo regInfo) throws ServiceException, ValidatorException {

		try {
			userValidator.checkRegInfo(regInfo);
			DAOUser.registration(regInfo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User authorization(String login, String password) throws ServiceException {

		try {
			User user = DAOUser.authorization(login, password);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public User getUser(String login) throws ServiceException {

		try {
			User user = DAOUser.getUser(login);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void delete(User user) throws ServiceException {
		try {
			DAOUser.delete(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

}
