package by.ftp.projectnews.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAONews;

public class DAONewsImpl implements DAONews {

	private List<News> newses = new ArrayList<News>();

	@Override
	public void addNewsSQL(News news) throws DAOException {
		newses.add(news);
		
	}

	@Override
	public List<News> getListOfNewsSQL() throws DAOException {

		return newses;
	}
	
}
