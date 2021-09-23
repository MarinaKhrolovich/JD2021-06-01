package by.ftp.projectnews.service.impl;

import java.util.List;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAONews;
import by.ftp.projectnews.dao.DAOProvider;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.validator.NewsFullCheck;
import by.ftp.projectnews.service.validator.ValidatorException;
import by.ftp.projectnews.service.validator.ValidatorProvider;

public class NewsServiceImpl implements NewsService {

	private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
	private static final DAONews DAONews = DAO_PROVIDER.getDaoNews();

	private static final ValidatorProvider VALIDATOR_PROVIDER = ValidatorProvider.getInstance();
	private static final NewsFullCheck newsValidator = VALIDATOR_PROVIDER.getNewValidator();

	@Override
	public void add(News news) throws ServiceException, ValidatorException {

		try {
			newsValidator.checkNews(news);
			DAONews.add(news);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void delete(News news) throws ServiceException {

		try {
			DAONews.delete(news);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void update(News news) throws ServiceException, ValidatorException {

		try {
			newsValidator.checkNews(news);
			DAONews.update(news);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<News> getListOfNews() throws ServiceException {

		try {
			return DAONews.getListOfNews();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> getListOfNews(String author) throws ServiceException {

		try {
			return DAONews.getListOfNews(author);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News getNews(String title) throws ServiceException {

		try {
			return DAONews.getNews(title);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News getNews(int id) throws ServiceException {
		
		try {
			return DAONews.getNews(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
