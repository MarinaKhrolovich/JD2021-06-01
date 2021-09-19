package by.ftp.projectnews.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {

	public RequestListener() {

	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		System.out.println(
				"Request for " + req.getRequestURI() + " with ID " + req.getRequestedSessionId() + " was destroyed.");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		System.out.println("Request from " + req.getRequestURI() + " with ID " + req.getRequestedSessionId()
				+ " was initialized.");
	}

}
