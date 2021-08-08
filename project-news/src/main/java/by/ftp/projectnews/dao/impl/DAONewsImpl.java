package by.ftp.projectnews.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAONews;

public class DAONewsImpl implements DAONews {

	private static final String DRIVER = "org.gjt.mm.mysql.Driver";
	private static final String PATH_TO_BASE = "jdbc:mysql://127.0.0.1/news_portal?useSSL=false";
	private static final String LOGIN_BASE = "root";
	private static final String PASSWORD_BASE = "Khrolovich1987";
	private static final String SELECT_ADD_NEWS = "INSERT INTO newses(title,brief,content,date,author) VALUES(?,?,?,?,?)";
	private static final String SELECT_GET_NEWS_ID = "SELECT * FROM newses WHERE id =?";
	private static final String SELECT_GET_NEWS_TITLE = "SELECT * FROM newses WHERE title =?";
	private static final String SELECT_GET_NEWS_AUTHOR = "SELECT * FROM newses WHERE author =?";
	private static final String SELECT_GET_LIST_OF_NEWS = "SELECT * FROM newses ORDER BY id DESC LIMIT 5";
	private static final String SELECT_GET_LIST_OF_NEWS_AUTHOR = "SELECT * FROM newses WHERE author =?";
	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String BRIEF = "brief";
	private static final String CONTENT = "content";
	private static final String AUTHOR = "author";
	private static final String MESSAGE_CONNECTION = "The news doesn't exists!";
	private static final String MESSAGE_NEWS = "The news doesn't exists!";

	@Override
	public void addNews(News news) throws DAOException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
			PreparedStatement ps = con.prepareStatement(SELECT_ADD_NEWS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			ps.setString(5, news.getAuthor());
			ps.executeUpdate();
			News newNews = this.getNews(news.getTitle());
			news.setId(newNews.getId());
		} catch (SQLException e) {
			throw new DAOException(MESSAGE_CONNECTION, e);
		}

	}

	@Override
	public List<News> getListOfNews() throws DAOException {

		List<News> newses = new ArrayList<News>();

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
		} catch (SQLException e) {
			throw new DAOException(MESSAGE_CONNECTION, e);
		}

		ResultSet rs;
		PreparedStatement ps = null;
		News newsFromBase = null;
		try {

			ps = con.prepareStatement(SELECT_GET_LIST_OF_NEWS);

			rs = ps.executeQuery();
			while (rs.next()) {
				newsFromBase = new News(rs.getInt(ID), rs.getString(TITLE), rs.getString(BRIEF), rs.getString(CONTENT),
						rs.getString(AUTHOR));
				if (!newses.contains(newsFromBase)) {
					newses.add(newsFromBase);
				}
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newses;
	}

	@Override
	public List<News> getListOfNews(String author) throws DAOException {

		List<News> newses = new ArrayList<News>();

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
		} catch (SQLException e) {
			throw new DAOException(MESSAGE_CONNECTION, e);
		}

		ResultSet rs;
		PreparedStatement ps = null;
		News newsFromBase = null;
		try {

			ps = con.prepareStatement(SELECT_GET_LIST_OF_NEWS_AUTHOR);
			ps.setString(1, author);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				newsFromBase = new News(rs.getInt(ID), rs.getString(TITLE), rs.getString(BRIEF), rs.getString(CONTENT),
						rs.getString(AUTHOR));
				if (!newses.contains(newsFromBase)) {
					newses.add(newsFromBase);
				}
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newses;
	}
	
	@Override
	public News getNews(int id) throws DAOException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
		} catch (SQLException e) {
			throw new DAOException(MESSAGE_CONNECTION, e);
		}

		ResultSet rs;
		PreparedStatement ps = null;
		News newsFromBase = null;
		try {

			ps = con.prepareStatement(SELECT_GET_NEWS_ID);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				newsFromBase = new News(rs.getInt(ID), rs.getString(TITLE), rs.getString(BRIEF), rs.getString(CONTENT),
						rs.getString(AUTHOR));
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newsFromBase;
	}

	public News getNews(String title) throws DAOException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
		} catch (SQLException e) {
			throw new DAOException(MESSAGE_CONNECTION, e);
		}

		ResultSet rs;
		PreparedStatement ps = null;
		News newsFromBase = null;
		try {
			ps = con.prepareStatement(SELECT_GET_NEWS_TITLE);
			ps.setString(1, title);

			rs = ps.executeQuery();
			while (rs.next()) {
				newsFromBase = new News(rs.getInt(ID), rs.getString(TITLE), rs.getString(BRIEF), rs.getString(CONTENT),
						rs.getString(AUTHOR));
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newsFromBase;

	}
}
