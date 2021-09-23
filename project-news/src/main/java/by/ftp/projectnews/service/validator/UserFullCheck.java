package by.ftp.projectnews.service.validator;

import by.ftp.projectnews.bean.RegistrationInfo;

public class UserFullCheck {

	private static final String LOGIN_CHECK = "^[a-zA-Z0-9_-]{3,200}$";
	private static final String PASSWORD_CHECK = "^.{3,15}$";
	private static final String NAME_CHECK = "^[a-zA-Zа-яА-ЯёЁ-]{3,45}$";
	private static final String YEAR_BIRTHDAY_CHECK = "^[0-9]{4}$";

	public void checkRegInfo(RegistrationInfo reginfo) throws ValidatorException {
		String login = reginfo.getLogin();
		String password = reginfo.getPassword();
		String name = reginfo.getName();
		String surName = reginfo.getSurName();
		String yearBirthday = reginfo.getYearBirthday();

		if (!login.matches(LOGIN_CHECK)) {
			throw new ValidatorException("Invalid login!");
		}

		if (!password.matches(PASSWORD_CHECK)) {
			throw new ValidatorException("Invalid password!");
		}

		if (!name.matches(NAME_CHECK)) {
			throw new ValidatorException("Invalid name!");
		}

		if (!surName.isEmpty()) {
			if (!surName.matches(NAME_CHECK)) {
				throw new ValidatorException("Invalid surName!");
			}
		}

		if (!yearBirthday.isEmpty()) {
			if (!yearBirthday.matches(YEAR_BIRTHDAY_CHECK)) {
				throw new ValidatorException("Invalid yearBirthday!");
			}
		}

	}
}
