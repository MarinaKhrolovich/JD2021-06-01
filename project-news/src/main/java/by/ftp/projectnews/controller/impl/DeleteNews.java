package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageLocal;
import by.ftp.projectnews.controller.message.MessageResourceManager;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteNews implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();
	private static final String USER = "user";
	private static final String URL = "url";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String ADMIN_ROLE = "admin";
	private static final String ID_NEWS = "id_news";
	private static final String PARAM_MESSAGE = "&message=";
	private static final String EMPTY_STRING = "";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = EMPTY_STRING;
		MessageResourceManager localManager = MessageResourceManager.getInstance();

		HttpSession session = request.getSession(false);
		String redirectAutho = CommandName.AUTHORIZATION.toString();

		if (session == null) {
			msg = localManager.getValue(MessageLocal.SESSION_LOST);
			String puth = CommandName.GO_TO_MAIN_PAGE.toString();
			response.sendRedirect(CONTROLLER_COMMAND + puth + PARAM_MESSAGE + msg);
			return;
		}

		User user = (User) session.getAttribute(USER);

		if (user == null) {
			msg = localManager.getValue(MessageLocal.MUST_SIGN_IN);

			response.sendRedirect(CONTROLLER_COMMAND + redirectAutho + PARAM_MESSAGE + msg);

			return;
		}

		if (!ADMIN_ROLE.equals(user.getRole())) {
			msg = localManager.getValue(MessageLocal.MUST_SIGN_IN_AS_ADMIN);

			session.removeAttribute(USER);
			// log
			response.sendRedirect(CONTROLLER_COMMAND + redirectAutho + PARAM_MESSAGE + msg);

			return;
		}

		String id_news = request.getParameter(ID_NEWS);
		if (id_news == null || id_news.isEmpty()) {

			msg = localManager.getValue(MessageLocal.NEWS_INCORRECT_ID);

			String commandName = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + commandName + PARAM_MESSAGE + msg);
			return;
		}
		try {

			msg = localManager.getValue(MessageLocal.NEWS_DELETE_SUCCESS);

			News newsToDelete = NEWS_SERVICE.getNews(Integer.parseInt(id_news));
			NEWS_SERVICE.delete(newsToDelete);
			String path = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);

		} catch (ServiceException e) {
			// log
			msg = localManager.getValue(MessageLocal.NEWS_DELETE_WRONG);

			String path = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
		}

	}

}
