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
import jakarta.servlet.http.HttpSession;

public class GoToNewsPage implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewService();
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String PAGE_NEWS_JSP = "/WEB-INF/jsp/pageNews.jsp";
	private static final String ID_NEWS = "id_news";
	private static final String URL = "url";
	private static final String NEWS = "news";
	private static final String MESSAGE = "message";
	private static final String MESSAGE_ERROR = "Error in the title of news";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = PAGE_NEWS_JSP;
		String id_news = request.getParameter(ID_NEWS);
		HttpSession session = request.getSession(true);
		try {
			News newsToShow = newsService.getNews(Integer.parseInt(id_news));
			session.setAttribute(NEWS, newsToShow);
			session.setAttribute(URL, CommandName.GO_TO_PAGE_NEWS.toString());

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			request.setAttribute(MESSAGE, MESSAGE_ERROR);
			request.getSession(true).setAttribute(URL, CommandName.UNKNOWN_COMMAND.toString());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

	}

}
