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

public class DeleteNews implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();
	private static final String USER = "user";
	private static final String URL = "url";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String ADMIN_ROLE = "admin";
	private static final String ID_NEWS = "id_news";

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
	
		try {
			News newsToDelete = NEWS_SERVICE.getNews(Integer.parseInt(id_news));
			NEWS_SERVICE.delete(newsToDelete);
			String path = (String) session.getAttribute(URL);
			response.sendRedirect(
					CONTROLLER_COMMAND + path + "&message=The news deleted successfully! ");

		} catch (ServiceException e) {
			// log
			String path = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + path + "&message=Something wrong at the deleting the news!");
		}


	}

}
