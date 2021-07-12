package by.ftp.projectnews.service.impl;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAONews;
import by.ftp.projectnews.dao.DAOProvider;
import by.ftp.projectnews.service.NewsService;

public class NewsServiceImpl implements NewsService {

	private static final DAOProvider daoProvider =DAOProvider.getInstance();
	private static final DAONews DAONews = daoProvider.getDaoNews();
	
	@Override
	public void add(News news) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(News news) {
		// TODO Auto-generated method stub
		
	}
}
