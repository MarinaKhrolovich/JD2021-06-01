package by.ftp.projectnews.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOUser;

public class DAOUserImpl implements DAOUser{

	private static final String DRIVER = "org.gjt.mm.mysql.Driver";
	private static final String PATH_TO_BASE = "jdbc:mysql://127.0.0.1/portal_news?useSSL=false";
	private static final String LOGIN_BASE = "root";
	private static final String PASSWORD_BASE = "12345";
		
	@Override
	public void registrationSQL(User user) throws DAOException {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		  
		Connection con;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
			String sql = "INSERT INTO users(login,password,role,name,surname,yearBirthday,sex) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			  
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole());
	    	ps.setString(4, user.getName());
	    	ps.setString(5, user.getSurName());
	    	ps.setInt(6, user.getYearBirthday());
	    	ps.setString(7, user.getSex());
			ps.executeUpdate();

		} catch (SQLException e) {
				e.printStackTrace();
		}
	
	}

	@Override
	public User authorizationSQL(String login) throws DAOException {

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
		User userFromBase = null;
		try {
			String sql = "SELECT * FROM users WHERE login =?"; 
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
		
			rs = ps.executeQuery(); 
			while (rs.next()) {
					userFromBase= new User();
					userFromBase.setLogin(rs.getString("login"));
					userFromBase.setRole(rs.getString("role"));
					userFromBase.setPassword(rs.getString("password"));
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userFromBase;
	
	}

	
	
	
}
