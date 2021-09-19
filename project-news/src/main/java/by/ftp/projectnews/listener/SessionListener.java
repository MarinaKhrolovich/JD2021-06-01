package by.ftp.projectnews.listener;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

public class SessionListener implements HttpSessionAttributeListener {

	public SessionListener() {

	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println(
				"added: " + event.getClass().getSimpleName() + " : " + event.getName() + " : " + event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println(
				"removed: " + event.getClass().getSimpleName() + " : " + event.getName() + " : " + event.getValue());

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println(
				"replaced: " + event.getClass().getSimpleName() + " : " + event.getName() + " : " + event.getValue());
	}

}
