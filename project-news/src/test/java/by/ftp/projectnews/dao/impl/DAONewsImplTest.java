package by.ftp.projectnews.dao.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAONews;
import by.ftp.projectnews.dao.DAOProvider;

public class DAONewsImplTest {

	private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
	private static final DAONews DAONews = DAO_PROVIDER.getDaoNews();

	@Test(expected = DAOException.class)
	public void addTest() throws DAOException {
		News newsException = new News();
		newsException.setId(5);
		newsException.setTitle("news for test");
		newsException.setBrief("news for test");
		newsException.setContent("news for test");
		newsException.setDate(Date.valueOf(LocalDate.now()));
		newsException.setActivity((byte) 1);
		newsException.setAuthor("");
		DAONews.add(newsException);
	}

	@Test
	public void addNewsTest() {
		News news = new News();
		news.setId(5);
		news.setTitle("news2");
		news.setBrief("news2");
		news.setContent("news2");
		news.setDate(Date.valueOf(LocalDate.now()));
		news.setActivity((byte) 1);
		news.setAuthor("");

		try {
			DAONews.add(news);
			News newsFromBase = DAONews.getNews(5);
			assertTrue(newsFromBase.equals(news));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
