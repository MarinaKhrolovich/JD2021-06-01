package by.ftp.projectnews.bean;

import java.io.Serializable;

public class RegistrationInfo extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String surName;
	private String yearBirthday;
	private String password;
	private String sex;

	public RegistrationInfo() {
		super();
	}

	public RegistrationInfo(String role, String name, String surName, String yearBirthday, String login, String password,
			String sex) {
		super();
		this.name = name;
		this.surName = surName;
		this.yearBirthday = yearBirthday;
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

	public String getYearBirthday() {
		return yearBirthday;
	}

	public void setYearBirthday(String yearBirthday) {
		this.yearBirthday = yearBirthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((yearBirthday == null) ? 0 : yearBirthday.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationInfo other = (RegistrationInfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (yearBirthday == null) {
			if (other.yearBirthday != null)
				return false;
		} else if (!yearBirthday.equals(other.sex))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [name=" + name + ", surname=" + surName + ", yearBirthday=" + yearBirthday
				+ ", password=" + password + ", sex=" + sex + "]";
	}

}
