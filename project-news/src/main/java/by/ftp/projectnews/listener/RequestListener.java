package by.ftp.projectnews.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {

	private final static Logger LOG = LogManager.getLogger(RequestListener.class);

	public RequestListener() {

	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();

		LOG.info("Request from " + req.getRequestURI() + " with ID " + req.getRequestedSessionId()
				+ " was initialized.");
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		LOG.info("Request for " + req.getRequestURI() + " with ID " + req.getRequestedSessionId() + " was destroyed.");
	}

}
