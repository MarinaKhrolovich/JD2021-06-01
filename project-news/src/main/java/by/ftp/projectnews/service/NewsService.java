package by.ftp.projectnews.service;

import java.util.List;

import by.ftp.projectnews.bean.News;

public interface NewsService {

	void add(News news) throws ServiceException;
	
	void delete(News news) throws ServiceException;

	List<News> getListOfNews() throws ServiceException;
	
	List<News> getListOfNews(String author) throws ServiceException;

	News getNews(String title) throws ServiceException;

	News getNews(int id) throws ServiceException;

	void update(News news) throws ServiceException;
}
