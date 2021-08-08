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

public class AddNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewService();
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String TITLE = "title";
	private static final String BRIEF = "brief";
	private static final String CONTENT = "content";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// this command can be used if only session was created
		HttpSession session = request.getSession(false);

		if (session == null) {
			request.getSession(true).setAttribute(URL, CommandName.AUTHORIZATION.toString());
			response.sendRedirect(
					"Controller?command=AUTHORIZATION&message=You session is lost.You must sign in to the system!");

			return;
		}

		User user = (User) session.getAttribute(USER);

		if (user == null) {
			request.getSession(true).setAttribute(URL, CommandName.AUTHORIZATION.toString());
			response.sendRedirect("Controller?command=AUTHORIZATION&message=You must sign in to the system!");

			return;
		}

		if (!"admin".equals(user.getRole())) {
			session.removeAttribute(USER);
			// log
			request.getSession(true).setAttribute(URL, CommandName.AUTHORIZATION.toString());
			response.sendRedirect("Controller?command=AUTHORIZATION&message=You must sign as an administrator'");

			return;
		}

		String title = request.getParameter(TITLE);
		String brief = request.getParameter(BRIEF);
		String content = request.getParameter(CONTENT);

		if ("".equals(title) || "".equals(brief) || "".equals(content)) {
			String path = (String) session.getAttribute(URL);
			response.sendRedirect("Controller?command=" + path + "&message=All fields should be fill!");
		}

		News news = new News();
		news.setTitle(title);
		news.setBrief(brief);
		news.setContent(content);
		news.setAuthor(user.getLogin());

		try {
			if (newsService.getNews(title) == null) {
				newsService.add(news);
				response.sendRedirect("Controller?command=go_to_page_news&id_news=" + String.valueOf(news.getId()));
			} else {
				String path = (String) session.getAttribute(URL);
				response.sendRedirect(
						"Controller?command=" + path + "&message=This title of news has already exists! Try again!");
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}
}
