package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageLocal;
import by.ftp.projectnews.controller.message.MessageResourceManager;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import by.ftp.projectnews.service.validator.ValidatorException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddNews implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();

	private final static Logger LOG = LogManager.getLogger(AddNews.class);

	private static final String URL = "url";
	private static final String USER = "user";
	private static final String TITLE = "title";
	private static final String BRIEF = "brief";
	private static final String CONTENT = "content";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String PARAM_MESSAGE = "&message=";
	private static final String PARAM_ID_NEWS = "&id_news=";
	private static final String EMPTY_STRING = "";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = EMPTY_STRING;
		MessageResourceManager localManager = MessageResourceManager.getInstance();

		HttpSession session = request.getSession(true);
		String commandName = CommandName.AUTHORIZATION.toString();
		User user = (User) session.getAttribute(USER);

		String title = request.getParameter(TITLE);
		String brief = request.getParameter(BRIEF);
		String content = request.getParameter(CONTENT);

		if (checkNullEmpty(title) || checkNullEmpty(brief) || checkNullEmpty(content)) {
			msg = localManager.getValue(MessageLocal.FILL_ALL_FIELDS);

			String path = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
			return;
		}

		News news = new News();
		news.setTitle(title);
		news.setBrief(brief);
		news.setContent(content);
		news.setAuthor(user.getLogin());
		news.setDate(Date.valueOf(LocalDate.now()));
		commandName = CommandName.GO_TO_PAGE_NEWS.toString();

		try {

			msg = localManager.getValue(MessageLocal.NEWS_ADD_SUCCESS);

			NEWS_SERVICE.add(news);
			News newNews = NEWS_SERVICE.getNews(title);
			response.sendRedirect(CONTROLLER_COMMAND + commandName + PARAM_ID_NEWS + String.valueOf(newNews.getId())
					+ PARAM_MESSAGE + msg);

		}catch (ValidatorException e) { 
			LOG.error(e);
			String path = (String) session.getAttribute(URL);
			msg = localManager.getValue(MessageLocal.VALIDATE_ERROR);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
		}
		catch (ServiceException e) {

			LOG.error(e);
			msg = localManager.getValue(MessageLocal.NEWS_ADD_TITLE_EXISTS);

			String path = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
		}

	}

	public boolean checkNullEmpty(String field) {

		return field == null || field.isEmpty();

	}
}
