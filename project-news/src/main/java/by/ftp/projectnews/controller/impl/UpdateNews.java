package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
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
	private static final String TITLE = "title";
	private static final String BRIEF = "brief";
	private static final String CONTENT = "content";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String commandName = CommandName.AUTHORIZATION.toString();

		if (session == null) {
			request.getSession(true).setAttribute(URL, commandName);
			response.sendRedirect(
					CONTROLLER_COMMAND + commandName + "&message=You session is lost.You must sign in to the system!");

			return;
		}

		User user = (User) session.getAttribute(USER);

		if (user == null) {
			request.getSession(true).setAttribute(URL, CommandName.AUTHORIZATION.toString());
			response.sendRedirect(CONTROLLER_COMMAND + commandName + "&message=You must sign in to the system!");

			return;
		}

		if (!ADMIN_ROLE.equals(user.getRole())) {
			session.removeAttribute(USER);
			// log
			request.getSession(true).setAttribute(URL, CommandName.AUTHORIZATION.toString());
			response.sendRedirect(CONTROLLER_COMMAND + commandName + "&message=You must sign as an administrator'");

			return;
		}

		String id_news = request.getParameter(ID_NEWS);

		String title = request.getParameter(TITLE);
		String brief = request.getParameter(BRIEF);
		String content = request.getParameter(CONTENT);

		if ("".equals(title) || "".equals(brief) || "".equals(content)) {
			String path = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + path + "&message=All fields should be fill!");
		}

		News newsToUpdate = new News();
		newsToUpdate.setId(Integer.parseInt(id_news));
		newsToUpdate.setTitle(title);
		newsToUpdate.setBrief(brief);
		newsToUpdate.setContent(content);

		try {
			NEWS_SERVICE.update(newsToUpdate);
			String path = CommandName.GO_TO_PAGE_NEWS.toString();
			response.sendRedirect(
					CONTROLLER_COMMAND + path + "&id_news=" + id_news + "&message=The news updated successfully!");

		} catch (ServiceException e) {
			// log
			String path = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + path + "&message=Something wrong at the updating the news!");
		}

	}

}
