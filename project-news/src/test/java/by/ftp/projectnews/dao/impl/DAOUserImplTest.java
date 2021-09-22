package by.ftp.projectnews.dao.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.dao.DAOException;
import by.ftp.projectnews.dao.DAOProvider;
import by.ftp.projectnews.dao.DAOUser;

public class DAOUserImplTest {

	private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
	private static final DAOUser DAOUser = DAO_PROVIDER.getDaoUser();
	
	@BeforeClass
	public static void Init() {
		
	}

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
	
}
