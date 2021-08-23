package by.ftp.projectnews.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOUser;
import by.ftp.projectnews.dao.connectionpool.ConnectionPool;
import by.ftp.projectnews.dao.connectionpool.ConnectionPoolException;

public class DAOUserImpl implements DAOUser {

	private static final ConnectionPool CONN_PULL = ConnectionPool.getInstance();
	private static final String SELECT_AUTHORIZATION = "SELECT * FROM users WHERE login =? AND password =?";
	private static final String SELECT_REGISTRATION = "INSERT INTO users(login,password,role,name,surname,yearBirthday,sex) VALUES(?,?,?,?,?,?,?)";
	private static final String LOGIN = "login";
	private static final String ROLE = "role";
	private static final String MESSAGE_USER_EXISTS = "This user has already exists";

	@Override
	public void registration(RegistrationInfo regInfo) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = CONN_PULL.takeConnection();
			ps = con.prepareStatement(SELECT_REGISTRATION);

			ps.setString(1, regInfo.getLogin());
			ps.setString(2, regInfo.getPassword());
			ps.setString(3, regInfo.getRole());
			ps.setString(4, regInfo.getName());
			ps.setString(5, regInfo.getSurName());
			ps.setInt(6, regInfo.getYearBirthday());
			ps.setString(7, regInfo.getSex());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(MESSAGE_USER_EXISTS, e);
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
			ps.setString(2, password);

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

}
