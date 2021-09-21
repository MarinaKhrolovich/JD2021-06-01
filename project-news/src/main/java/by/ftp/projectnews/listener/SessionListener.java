package by.ftp.projectnews.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

public class SessionListener implements HttpSessionAttributeListener {

	private final static Logger LOG = LogManager.getLogger(SessionListener.class);

	public SessionListener() {

	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		LOG.info("added: " + event.getClass().getSimpleName() + " : " + event.getName() + " : " + event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		LOG.info("removed: " + event.getClass().getSimpleName() + " : " + event.getName() + " : " + event.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		LOG.info("replaced: " + event.getClass().getSimpleName() + " : " + event.getName() + " : " + event.getValue());
	}

}
