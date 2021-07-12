package by.ftp.projectnews.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOUser;

public class DAOUserImpl implements DAOUser{

	// именно в этом методt мы связываемся с базой данных и проверяем корректность логина и пароля
	
	private HashMap<String,User> ListOfUsers = new HashMap<>();
	
	@Override
	public void registrationSQL(User user) throws DAOException {
		ListOfUsers.put(user.getLogin(),user);
	}

	@Override
	public User authorizationSQL(String login, String password) throws DAOException {

		User user = ListOfUsers.get(login);
		if (user.getPassword() == password) {
			return user;
		}
		return null;
	}
	
}
