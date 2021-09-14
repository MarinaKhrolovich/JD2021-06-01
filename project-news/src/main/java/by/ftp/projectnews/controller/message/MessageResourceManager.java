package by.ftp.projectnews.controller.message;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageResourceManager {

	private final static String LOCAL = "localization.local";

	private final static MessageResourceManager instance = new MessageResourceManager();

	private ResourceBundle resourceBundle;

	
	public static MessageResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return URLEncoder.encode(resourceBundle.getString(key),StandardCharsets.UTF_8);
	}
	
	public void setLocale(String locale) {
		resourceBundle = ResourceBundle.getBundle(LOCAL, new Locale(locale));
	}
}
