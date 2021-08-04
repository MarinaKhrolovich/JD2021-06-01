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
	private static final String PATH_TO_BASE = "jdbc:mysql://127.0.0.1/portal_news?useSSL=false";
	private static final String LOGIN_BASE = "root";
	private static final String PASSWORD_BASE = "12345";
 
	
	@Override
	public void addNewsSQL(News news) throws DAOException {

		 try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		  
		Connection con;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
			String sql = "INSERT INTO newses(title,brief,content,date) VALUES(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			  
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
	    	ps.setDate(4, Date.valueOf(LocalDate.now())); 
			ps.executeUpdate();
			News newNews = this.getNewsSQL(news.getTitle());
			news.setId(newNews.getId());
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public List<News> getListOfNewsSQL() throws DAOException {

		List<News> newses = new ArrayList<News>();
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE,LOGIN_BASE,PASSWORD_BASE);
		} catch (SQLException e) {
			throw new DAOException("Connection doesn't exists");
		}


		ResultSet rs;
		PreparedStatement ps = null;
		News newsFromBase = null;
		try {
			String sql = "SELECT * FROM newses ORDER BY id DESC LIMIT 5"; 
			ps = con.prepareStatement(sql);
		
			rs = ps.executeQuery(); 
			while (rs.next()) {
				newsFromBase= new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"),rs.getString("content"));
				if(!newses.contains(newsFromBase)) {
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
	public News getNewsSQL(int id) throws DAOException {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE,LOGIN_BASE,PASSWORD_BASE);
		} catch (SQLException e) {
			throw new DAOException("Connection doesn't exists");
		}


		ResultSet rs;
		PreparedStatement ps = null;
		News newsFromBase = null;
		try {
			String sql = "SELECT * FROM newses WHERE id =?"; 
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
				
			rs = ps.executeQuery(); 
			while (rs.next()) {
				newsFromBase= new News(rs.getInt("id"), rs.getString("title"),rs.getString("brief"),rs.getString("content"));
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		if (newsFromBase!=null) {
			return newsFromBase;
		}
		
		
		throw new DAOException("The news doesn't exists!");
	}
	
	public News getNewsSQL(String title) throws DAOException {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE,LOGIN_BASE,PASSWORD_BASE);
		} catch (SQLException e) {
			throw new DAOException("Connection doesn't exists");
		}


		ResultSet rs;
		PreparedStatement ps = null;
		News newsFromBase = null;
		try {
			String sql = "SELECT * FROM newses WHERE title =?"; 
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
		
			rs = ps.executeQuery(); 
			while (rs.next()) {
				newsFromBase= new News(rs.getInt("id"), rs.getString("title"),rs.getString("brief"),rs.getString("content"));
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	

		if (newsFromBase!=null) {
			return newsFromBase;
		}
		
		
		throw new DAOException("The news doesn't exists!");
	}
}
