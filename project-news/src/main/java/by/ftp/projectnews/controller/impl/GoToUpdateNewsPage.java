package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageLocal;
import by.ftp.projectnews.controller.message.MessageResourceManager;
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

	private final static Logger LOG = LogManager.getLogger(GoToUpdateNewsPage.class);
	
	private static final String UPDATE_NEWS_PAGE_JSP = "/WEB-INF/jsp/updateNews.jsp";
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String URL = "url";
	private static final String ID_NEWS = "id_news";
	private static final String PARAM_ID_NEWS = "&id_news=";
	private static final String NEWS = "news";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String PARAM_MESSAGE = "&message=";
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MessageResourceManager localManager = MessageResourceManager.getInstance();

		HttpSession session = request.getSession(true);
		String id_news = request.getParameter(ID_NEWS);

		if (id_news == null || id_news.isEmpty()) {
			String commandName = (String) session.getAttribute(URL);
			String msg = localManager.getValue(MessageLocal.NEWS_INCORRECT_ID);
			response.sendRedirect(CONTROLLER_COMMAND + commandName + PARAM_MESSAGE + msg);
			return;
		}
		try {
			News newsToUpdate = NEWS_SERVICE.getNews(Integer.parseInt(id_news));
			request.setAttribute(NEWS, newsToUpdate);
			session.setAttribute(URL, CommandName.GO_TO_UPDATE_NEWS_PAGE.toString() + PARAM_ID_NEWS + id_news);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(UPDATE_NEWS_PAGE_JSP);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			LOG.error(e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

	}

}
