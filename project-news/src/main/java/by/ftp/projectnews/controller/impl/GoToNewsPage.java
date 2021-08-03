package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.controller.Command;
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
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = PAGE_NEWS_JSP;
		String title = request.getParameter("title");
		try {
			News newsToShow = newsService.getNews(title);
			request.getSession(true).setAttribute("news",newsToShow);
			request.getSession(true).setAttribute("url", "GO_TO_NEWS_PAGE");
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			request.setAttribute("message", "Error in the title of news");
			request.getSession(true).setAttribute("url", "UNKNOWN_COMMAND");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

		
	}
	
	
}
