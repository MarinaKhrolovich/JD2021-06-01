package by.ftp.projectnews.dao.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {

	private final static String DB = "by.ftp.projectnews.dao.connectionpool.db";
	
	private final static DBResourceManager instance = new DBResourceManager();
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(DB);

	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return resourceBundle.getString(key);
	}
}
