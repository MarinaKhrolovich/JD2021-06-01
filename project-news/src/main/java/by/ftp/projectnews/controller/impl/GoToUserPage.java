package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToUserPage implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();

	private final static Logger LOG = LogManager.getLogger(GoToUserPage.class);
	
	private static final String USERPROFILE_JSP = "/WEB-INF/jsp/userprofile.jsp";
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String URL = "url";
	private static final String NEWSES_OF_AUTHOR = "newses_author";
	private static final String USER = "user";
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(USER);
		try {
			request.setAttribute(NEWSES_OF_AUTHOR, NEWS_SERVICE.getListOfNews(user.getLogin()));
			session.setAttribute(URL, CommandName.GO_TO_USER_PAGE.toString());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(USERPROFILE_JSP);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			LOG.error(e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

	}

}
