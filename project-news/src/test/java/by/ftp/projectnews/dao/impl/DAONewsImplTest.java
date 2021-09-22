package by.ftp.projectnews.dao.impl;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAONews;
import by.ftp.projectnews.dao.DAOProvider;

public class DAONewsImplTest {

	private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
	private static final DAONews DAONews = DAO_PROVIDER.getDaoNews();

	@BeforeClass
	public static void Init() {

	}

	@Test(expected = DAOException.class)
	public void addTest() throws DAOException {
		News news = new News();
		news.setId(5);
		news.setTitle("news1");
		news.setBrief("news1");
		news.setContent("news1");
		news.setDate(Date.valueOf(LocalDate.now()));
		news.setActivity((byte) 1);
		news.setAuthor("");
		DAONews.add(news);
	}

}
