package by.ftp.projectnews.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOUser;

public class DAOUserImpl implements DAOUser {

	private static final String DRIVER = "org.gjt.mm.mysql.Driver";
	private static final String PATH_TO_BASE = "jdbc:mysql://127.0.0.1/news_portal?useSSL=false";
	private static final String LOGIN_BASE = "root";
	private static final String PASSWORD_BASE = "Khrolovich1987";
	private static final String SELECT_AUTHORIZATION = "SELECT * FROM users WHERE login =? AND password =?";
	private static final String SELECT_REGISTRATION = "INSERT INTO users(login,password,role,name,surname,yearBirthday,sex) VALUES(?,?,?,?,?,?,?)";
	private static final String LOGIN = "login";
	private static final String ROLE = "role";

	@Override
	public void registration(RegistrationInfo regInfo) throws DAOException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
			PreparedStatement ps = con.prepareStatement(SELECT_REGISTRATION);

			ps.setString(1, regInfo.getLogin());
			ps.setString(2, regInfo.getPassword());
			ps.setString(3, regInfo.getRole());
			ps.setString(4, regInfo.getName());
			ps.setString(5, regInfo.getSurName());
			ps.setInt(6, regInfo.getYearBirthday());
			ps.setString(7, regInfo.getSex());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User authorization(String login, String password) throws DAOException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(PATH_TO_BASE, LOGIN_BASE, PASSWORD_BASE);
		} catch (SQLException e) {
			throw new DAOException("Connection doesn't exists");
		}

		ResultSet rs;
		PreparedStatement ps = null;
		User userFromBase = null;
		try {
			ps = con.prepareStatement(SELECT_AUTHORIZATION);
			ps.setString(1, login);
			ps.setString(2, password);

			rs = ps.executeQuery();
			while (rs.next()) {
				userFromBase = new User();
				userFromBase.setLogin(rs.getString(LOGIN));
				userFromBase.setRole(rs.getString(ROLE));
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userFromBase;

	}

}
