package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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

public class GoToNewsPage implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();

	private final static Logger LOG = LogManager.getLogger(GoToNewsPage.class);
	
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String PAGE_NEWS_JSP = "/WEB-INF/jsp/pageNews.jsp";
	private static final String ID_NEWS = "id_news";
	private static final String URL = "url";
	private static final String NEWS = "news";
	private static final String MESSAGE = "message";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String PARAM_MESSAGE = "&message=";
	private static final String EMPTY_STRING = "";
	private static final String PARAM_ID_NEWS = "&id_news=";
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = PAGE_NEWS_JSP;
		String msg = EMPTY_STRING;
		String id_news = request.getParameter(ID_NEWS);
		HttpSession session = request.getSession(true);

		MessageResourceManager localManager = MessageResourceManager.getInstance();

		if (id_news == null || id_news.isEmpty()) {
			msg = localManager.getValue(MessageLocal.NEWS_INCORRECT_ID);

			String commandName = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + commandName + PARAM_MESSAGE + msg);
			return;
		}

		try {
			News newsToShow = NEWS_SERVICE.getNews(Integer.parseInt(id_news));
			request.setAttribute(NEWS, newsToShow);
			session.setAttribute(URL, CommandName.GO_TO_PAGE_NEWS.toString() + PARAM_ID_NEWS + id_news);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			LOG.error(e);
			msg = URLDecoder.decode(localManager.getValue(MessageLocal.NEWS_INCORRECT_TITLE), StandardCharsets.UTF_8);
			request.setAttribute(MESSAGE, msg);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

	}

}
