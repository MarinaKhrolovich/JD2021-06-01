package by.ftp.projectnews.service.impl;

import java.util.List;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAONews;
import by.ftp.projectnews.dao.DAOProvider;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;

public class NewsServiceImpl implements NewsService {

	private static final DAOProvider daoProvider =DAOProvider.getInstance();
	private static final DAONews DAONews = daoProvider.getDaoNews();
	
	@Override
	public void add(News news) throws ServiceException  {
		try {
			DAONews.addNewsSQL(news);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<News> getListOfNews() throws ServiceException {
		
		try {
			return DAONews.getListOfNewsSQL();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	
	@Override
	public News getNews(String title) throws ServiceException {
		//check parameters!
		if (title ==null||title.isEmpty()) {
			throw new ServiceException("Incorrect title of news!");
		}
		try {
			return DAONews.getNewsSQL(title);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(News news) throws ServiceException  {
		// TODO Auto-generated method stub
		
	}
}
