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

public class UpdateNews implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();
	private static final String USER = "user";
	private static final String URL = "url";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String ADMIN_ROLE = "admin";
	private static final String ID_NEWS = "id_news";
	private static final String PARAM_ID_NEWS = "&id_news=";
	private static final String TITLE = "title";
	private static final String BRIEF = "brief";
	private static final String CONTENT = "content";
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

		String title = request.getParameter(TITLE);
		String brief = request.getParameter(BRIEF);
		String content = request.getParameter(CONTENT);

		if (EMPTY_STRING.equals(title) || EMPTY_STRING.equals(brief) || EMPTY_STRING.equals(content)) {
			String path = (String) session.getAttribute(URL);
			msg = localManager.getValue(MessageLocal.FILL_FIELDS);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
			return;
		}

		News newsToUpdate = new News();
		newsToUpdate.setId(Integer.parseInt(id_news));
		newsToUpdate.setTitle(title);
		newsToUpdate.setBrief(brief);
		newsToUpdate.setContent(content);

		try {
			News newsFromBase = NEWS_SERVICE.getNews(title);
			if (newsFromBase != null) {
				if (Integer.parseInt(id_news) != newsFromBase.getId()) {

					msg = localManager.getValue(MessageLocal.NEWS_ADD_TITLE_EXISTS);

					String path = (String) session.getAttribute(URL);
					response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
					return;
				}
			}

			NEWS_SERVICE.update(newsToUpdate);
			String path = CommandName.GO_TO_PAGE_NEWS.toString();
			msg = localManager.getValue(MessageLocal.NEWS_UPDATE_SUCCESS);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_ID_NEWS + id_news + PARAM_MESSAGE + msg);

		} catch (ServiceException e) {
			// log
			String path = (String) session.getAttribute(URL);
			msg = localManager.getValue(MessageLocal.NEWS_UPDATE_WRONG);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
		}

	}

}
