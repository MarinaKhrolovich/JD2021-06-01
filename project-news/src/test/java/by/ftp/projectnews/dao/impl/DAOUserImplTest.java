package by.ftp.projectnews.dao.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOProvider;
import by.ftp.projectnews.dao.DAOUser;

public class DAOUserImplTest {

	private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
	private static final DAOUser DAOUser = DAO_PROVIDER.getDaoUser();

	@Test(expected = DAOException.class)
	public void registrationTest() throws DAOException {
		RegistrationInfo regInfo = new RegistrationInfo();
		regInfo.setLogin("Artyom");
		regInfo.setName("Artyom");
		regInfo.setPassword("qwerty");
		regInfo.setRole("user");
		regInfo.setSex("m");
		regInfo.setSurName("");
		regInfo.setYearBirthday(1990);
		DAOUser.registration(regInfo);
	}
	
	@Test
	public void addUserTest() {
		RegistrationInfo regInfo = new RegistrationInfo();
		regInfo.setLogin("Artyom1");
		regInfo.setName("Artyom");
		regInfo.setPassword("qwerty");
		regInfo.setRole("user");
		regInfo.setSex("m");
		regInfo.setSurName("");
		regInfo.setYearBirthday(1990);
		
		User user = new User();
		user.setLogin("Artyom1");
		user.setRole("user");

		try {
			DAOUser.registration(regInfo);
			User userFromBase = DAOUser.getUser(regInfo.getLogin());
			assertTrue(user.equals(userFromBase));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
