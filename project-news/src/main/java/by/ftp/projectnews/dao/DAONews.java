package by.ftp.projectnews.dao;

import java.util.List;

import by.ftp.projectnews.bean.News;

public interface DAONews {

	void addNews(News news) throws DAOException;

	List<News> getListOfNews() throws DAOException;

	News getNews(int id) throws DAOException;

	News getNews(String title) throws DAOException;
}
