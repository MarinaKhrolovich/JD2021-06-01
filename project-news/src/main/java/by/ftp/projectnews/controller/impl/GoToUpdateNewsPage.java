package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.News;
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

public class GoToUpdateNewsPage implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();
	
	private static final String UPDATE_NEWS_PAGE_JSP = "/WEB-INF/jsp/updateNews.jsp";
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String URL = "url";
	private static final String ID_NEWS = "id_news";
	private static final String NEWS = "news";
	private static final String MESSAGE_ERROR_ID = "Incorrect id of news!";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String id_news = request.getParameter(ID_NEWS);
		session.setAttribute(URL, CommandName.GO_TO_UPDATE_NEWS_PAGE.toString());
		if (id_news == null || id_news.isEmpty()) {
			String commandName = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + commandName + "&message=" + MESSAGE_ERROR_ID);
			return;
		}
		try {
			News newsToUpdate = NEWS_SERVICE.getNews(Integer.parseInt(id_news));
			request.setAttribute(NEWS, newsToUpdate);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(UPDATE_NEWS_PAGE_JSP);
			requestDispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			//log
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}
		
	}

}
