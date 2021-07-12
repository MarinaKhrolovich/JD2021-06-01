package by.ftp.projectnews.bean;

public class RegistrationInfo {

    private User user;
	private String name;
	private String surName;
	private int yearBirthday;
	private String login;
	private String sex;
	
	public RegistrationInfo(){}

	
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
}
