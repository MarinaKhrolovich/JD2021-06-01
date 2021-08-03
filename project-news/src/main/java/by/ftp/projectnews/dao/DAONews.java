package by.ftp.projectnews.dao;

import java.util.List;

import by.ftp.projectnews.bean.News;

public interface DAONews {

	void addNewsSQL(News news) throws DAOException;
	List<News> getListOfNewsSQL() throws DAOException;

	News getNewsSQL(String title) throws DAOException;
}
