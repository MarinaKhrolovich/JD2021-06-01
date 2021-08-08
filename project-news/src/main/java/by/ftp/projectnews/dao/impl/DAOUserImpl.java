package by.ftp.projectnews.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOUser;
import by.ftp.projectnews.dao.connectionpool.ConnectionPoolException;
import by.ftp.projectnews.dao.connectionpool.ConnectionPool;
import by.ftp.projectnews.dao.connectionpool.DBParameter;
import by.ftp.projectnews.dao.connectionpool.DBResourceManager;

public class DAOUserImpl implements DAOUser {

	private static final DBResourceManager DB = DBResourceManager.getInstance();
	
	private static final String SELECT_AUTHORIZATION = "SELECT * FROM users WHERE login =? AND password =?";
	private static final String SELECT_REGISTRATION = "INSERT INTO users(login,password,role,name,surname,yearBirthday,sex) VALUES(?,?,?,?,?,?,?)";
	private static final String LOGIN = "login";
	private static final String ROLE = "role";

	@Override
	public void registration(RegistrationInfo regInfo) throws DAOException {

		try {
			Connection con =ConnectionPool.getInstance().takeConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_REGISTRATION);
					
			Class.forName(DB.getValue(DBParameter.DB_DRIVER));

			ps.setString(1, regInfo.getLogin());
			ps.setString(2, regInfo.getPassword());
			ps.setString(3, regInfo.getRole());
			ps.setString(4, regInfo.getName());
			ps.setString(5, regInfo.getSurName());
			ps.setInt(6, regInfo.getYearBirthday());
			ps.setString(7, regInfo.getSex());
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public User authorization(String login, String password) throws DAOException {

		try {
			Connection con = ConnectionPool.getInstance().takeConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_AUTHORIZATION);
			
			User userFromBase = null;
			Class.forName(DB.getValue(DBParameter.DB_DRIVER));
			ps = con.prepareStatement(SELECT_AUTHORIZATION);
			ps.setString(1, login);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userFromBase = new User();
				userFromBase.setLogin(rs.getString(LOGIN));
				userFromBase.setRole(rs.getString(ROLE));
			}
			rs.close();
			con.close();
			return userFromBase;
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (ClassNotFoundException e) {
			throw new DAOException(e);
		}

	}

}
