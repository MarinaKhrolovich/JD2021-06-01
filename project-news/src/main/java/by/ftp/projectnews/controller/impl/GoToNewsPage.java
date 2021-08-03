package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsPage implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewService();
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String PAGE_NEWS_JSP = "/WEB-INF/jsp/pageNews.jsp";
	private static final String TITLE = "title";
	private static final String URL = "url";
	private static final String NEWS = "news";
	private static final String MESSAGE = "message";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = PAGE_NEWS_JSP;
		String title = request.getParameter(TITLE);
		try {
			News newsToShow = newsService.getNews(title);
			request.getSession(true).setAttribute(NEWS,newsToShow);
			request.getSession(true).setAttribute(URL, CommandName.GO_TO_PAGE_NEWS.toString());
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			request.setAttribute(MESSAGE, "Error in the title of news");
			request.getSession(true).setAttribute(URL, CommandName.UNKNOWN_COMMAND.toString());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

		
	}
	
	
}
