package by.ftp.projectnews.bean;

public class RegistrationInfo extends User {

	private String role;
	private String name;
	private String surName;
	private int yearBirthday;
	private String login;
	private String password;
	private String sex;

	public RegistrationInfo() {
		super();
	}

	public RegistrationInfo(String role, String name, String surName, int yearBirthday, String login, String password,
			String sex) {
		super();
		this.role = role;
		this.name = name;
		this.surName = surName;
		this.yearBirthday = yearBirthday;
		this.login = login;
		this.password = password;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getYearBirthday() {
		return yearBirthday;
	}

	public void setYearBirthday(int yearBirthday) {
		this.yearBirthday = yearBirthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
