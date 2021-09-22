package by.ftp.projectnews.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAONews;
import by.ftp.projectnews.dao.connectionpool.ConnectionPool;
import by.ftp.projectnews.dao.connectionpool.ConnectionPoolException;

public class DAONewsImpl implements DAONews {

	private static final ConnectionPool CONN_PULL = ConnectionPool.getInstance();
	private static final String SELECT_ADD_NEWS = "INSERT INTO newses(title,brief,content,date,author) VALUES(?,?,?,?,?)";
	private static final String SELECT_GET_NEWS_ID = "SELECT * FROM newses WHERE id =?";
	private static final String DELETE_NEWS = "DELETE FROM newses WHERE id =?";
	private static final String UPDATE_NEWS = "UPDATE newses SET title = ?,brief = ?, content = ? WHERE id =?";
																												
																												
	private static final String SELECT_GET_NEWS_TITLE = "SELECT * FROM newses WHERE title =?";
	private static final String SELECT_GET_LIST_OF_NEWS = "SELECT * FROM newses ORDER BY id DESC";
	private static final String SELECT_GET_LIST_OF_NEWS_AUTHOR = "SELECT * FROM newses WHERE author =? ORDER BY id DESC";
	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String BRIEF = "brief";
	private static final String CONTENT = "content";
	private static final String AUTHOR = "author";
	private static final String DATE = "date";

	@Override
	public void add(News news) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = CONN_PULL.takeConnection();
			ps = con.prepareStatement(SELECT_ADD_NEWS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setDate(4, news.getDate());
			ps.setString(5, news.getAuthor());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps);
		}

	}

	@Override
	public void delete(News news) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = CONN_PULL.takeConnection();
			ps = con.prepareStatement(DELETE_NEWS);

			ps.setInt(1, news.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps);
		}

	}

	@Override
	public void update(News news) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = CONN_PULL.takeConnection();
			ps = con.prepareStatement(UPDATE_NEWS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setInt(4, news.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps);
		}

	}

	@Override
	public List<News> getListOfNews() throws DAOException {

		List<News> newses = new ArrayList<News>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		News newsFromBase = null;

		try {
			con = CONN_PULL.takeConnection();

			ps = con.prepareStatement(SELECT_GET_LIST_OF_NEWS);
			rs = ps.executeQuery();
			while (rs.next()) {
				newsFromBase = new News(rs.getInt(ID), rs.getString(TITLE), rs.getString(BRIEF), rs.getString(CONTENT),
						rs.getString(AUTHOR), rs.getDate(DATE));
				if (!newses.contains(newsFromBase)) {
					newses.add(newsFromBase);
				}
			}
			return newses;
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps, rs);
		}

	}

	@Override
	public List<News> getListOfNews(String author) throws DAOException {

		List<News> newses = new ArrayList<News>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		News newsFromBase = null;
		try {
			con = CONN_PULL.takeConnection();

			ps = con.prepareStatement(SELECT_GET_LIST_OF_NEWS_AUTHOR);
			ps.setString(1, author);
			rs = ps.executeQuery();
			while (rs.next()) {
				newsFromBase = new News(rs.getInt(ID), rs.getString(TITLE), rs.getString(BRIEF), rs.getString(CONTENT),
						rs.getString(AUTHOR), rs.getDate(DATE));
				if (!newses.contains(newsFromBase)) {
					newses.add(newsFromBase);
				}
			}
			return newses;
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps, rs);
		}

	}

	@Override
	public News getNews(int id) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		News newsFromBase = null;

		try {
			con = CONN_PULL.takeConnection();

			ps = con.prepareStatement(SELECT_GET_NEWS_ID);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				newsFromBase = new News(rs.getInt(ID), rs.getString(TITLE), rs.getString(BRIEF), rs.getString(CONTENT),
						rs.getString(AUTHOR), rs.getDate(DATE));
			}
			return newsFromBase;
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps, rs);
		}

	}

	public News getNews(String title) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		News newsFromBase = null;

		try {
			con = CONN_PULL.takeConnection();

			ps = con.prepareStatement(SELECT_GET_NEWS_TITLE);
			ps.setString(1, title);
			rs = ps.executeQuery();
			while (rs.next()) {
				newsFromBase = new News(rs.getInt(ID), rs.getString(TITLE), rs.getString(BRIEF), rs.getString(CONTENT),
						rs.getString(AUTHOR), rs.getDate(DATE));
			}
			return newsFromBase;

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps, rs);
		}

	}
}
