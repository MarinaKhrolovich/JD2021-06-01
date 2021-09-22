package by.ftp.projectnews.dao.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAONews;
import by.ftp.projectnews.dao.DAOProvider;

public class DAONewsImplTest {

	private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
	private static final DAONews DAONews = DAO_PROVIDER.getDaoNews();
	private static News news;
	
	@BeforeClass
	public static void initT() {
		news = new News();
		news.setTitle("news2");
		news.setBrief("news2");
		news.setContent("news2");
		news.setDate(Date.valueOf(LocalDate.now()));
		news.setAuthor("Test");	
	}
	
	@AfterClass
	public static void destroyT() {
		try {
			DAONews.delete(news);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = DAOException.class)
	public void addTest() throws DAOException {
		News newsException = new News();
		newsException.setTitle("news for test");
		newsException.setBrief("news for test");
		newsException.setContent("news for test");
		newsException.setDate(Date.valueOf(LocalDate.now()));
		newsException.setAuthor("Test");
		DAONews.add(newsException);
	}

	@Test
	public void addNewsTest() {
		
		try {
			DAONews.add(news);
			News newsFromBase = DAONews.getNews(news.getTitle());
			news.setId(newsFromBase.getId());
			assertTrue(newsFromBase.equals(news));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
