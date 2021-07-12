package by.ftp.projectnews.service;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.service.ServiceException;

public interface NewsService {

	void add(News news) throws ServiceException;

	void update(News news) throws ServiceException;
}
