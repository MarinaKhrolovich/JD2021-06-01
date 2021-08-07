package by.ftp.projectnews.dao;

import by.ftp.projectnews.dao.impl.DAONewsImpl;
import by.ftp.projectnews.dao.impl.DAOUserImpl;

public final class DAOProvider {
	public static final DAOProvider instance = new DAOProvider();

	private final DAOUser daoUser = new DAOUserImpl();
	private final DAONews daoNews = new DAONewsImpl();

	public DAOProvider() {
		super();
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public DAOUser getDaoUser() {
		return daoUser;
	}

	public DAONews getDaoNews() {
		return daoNews;
	}

}
