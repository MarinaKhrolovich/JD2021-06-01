package by.ftp.projectnews.dao;

import java.util.List;

import by.ftp.projectnews.bean.News;

public interface DAONews {

	void add(News news) throws DAOException;
	
	void delete(News news) throws DAOException;

	List<News> getListOfNews() throws DAOException;
	
	List<News> getListOfNews(String author) throws DAOException;

	News getNews(int id) throws DAOException;

	News getNews(String title) throws DAOException;
}
