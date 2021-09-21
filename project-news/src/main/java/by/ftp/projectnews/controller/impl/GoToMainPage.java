package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageResourceManager;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {

	private static final String MAIN_JSP = "/WEB-INF/jsp/main.jsp";
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();
	private static final String URL = "url";
	private static final String NEWSES = "newses";
	private static final String LOCAL = "local";
	private static final String LOCAL_BE = "be";
	private final static Logger LOG = LogManager.getLogger(GoToMainPage.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MessageResourceManager localManager = MessageResourceManager.getInstance();
		HttpSession session = request.getSession(true);
		session.setAttribute(URL, CommandName.GO_TO_MAIN_PAGE.toString());

		String local = (String) session.getAttribute(LOCAL);
		if (local == null) {
			session.setAttribute(LOCAL, LOCAL_BE);
			localManager.setLocale(LOCAL_BE);
		}

		try {
			request.setAttribute(NEWSES, NEWS_SERVICE.getListOfNews());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_JSP);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			LOG.error(e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

	}
}
