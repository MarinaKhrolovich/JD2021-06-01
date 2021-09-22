package by.ftp.projectnews.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOUser;
import by.ftp.projectnews.dao.connectionpool.ConnectionPool;
import by.ftp.projectnews.dao.connectionpool.ConnectionPoolException;

public class DAOUserImpl implements DAOUser {

	private static final ConnectionPool CONN_PULL = ConnectionPool.getInstance();
	
	private static final String SELECT_AUTHORIZATION = "SELECT * FROM users WHERE login =?";// AND password =?";
	private static final String SELECT_REGISTRATION = "INSERT INTO users(login,password,role,name,surname,yearBirthday,sex) VALUES(?,?,?,?,?,?,?)";
	private static final String SELECT_GET_USER_ID = "SELECT * FROM users WHERE login =?";
	private static final String DELETE_USER = "DELETE FROM users WHERE login =?";
	
	private static final String LOGIN = "login";
	private static final String ROLE = "role";
	private static final String PASSWORD = "password";
	
	
	@Override
	public void registration(RegistrationInfo regInfo) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = CONN_PULL.takeConnection();
			ps = con.prepareStatement(SELECT_REGISTRATION);

			ps.setString(1, regInfo.getLogin());

			String salt = BCrypt.gensalt();
			String hashpw = BCrypt.hashpw(regInfo.getPassword(), salt);

			ps.setString(2, hashpw);
			ps.setString(3, regInfo.getRole());
			ps.setString(4, regInfo.getName());
			ps.setString(5, regInfo.getSurName());
			ps.setInt(6, regInfo.getYearBirthday());
			ps.setString(7, regInfo.getSex());
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
	public User authorization(String login, String password) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = CONN_PULL.takeConnection();
			ps = con.prepareStatement(SELECT_AUTHORIZATION);

			User userFromBase = null;
			ps = con.prepareStatement(SELECT_AUTHORIZATION);
			ps.setString(1, login);

			rs = ps.executeQuery();
			while (rs.next()) {
				if (BCrypt.checkpw(password, rs.getString(PASSWORD))) {
					userFromBase = new User();
					userFromBase.setLogin(rs.getString(LOGIN));
					userFromBase.setRole(rs.getString(ROLE));
				}
			}
			return userFromBase;
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps, rs);
		}

	}

	@Override
	public User getUser(String login) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User userFromBase = null;

		try {
			con = CONN_PULL.takeConnection();

			ps = con.prepareStatement(SELECT_GET_USER_ID);
			ps.setString(1, login);

			rs = ps.executeQuery();
			while (rs.next()) {
				userFromBase = new User();
				userFromBase.setLogin(rs.getString(LOGIN));
				userFromBase.setRole(rs.getString(ROLE));
			}
			return userFromBase;
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps, rs);
		}

	}
	
	@Override
	public void delete(User user) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = CONN_PULL.takeConnection();
			ps = con.prepareStatement(DELETE_USER);

			ps.setString(1, user.getLogin());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CONN_PULL.closeConnection(con, ps);
		}
		
	}
	
}
