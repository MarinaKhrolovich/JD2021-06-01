package by.ftp.projectnews.controller.impl;

import java.io.IOException;

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

public class GoToUserPage implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewService();
	
	private static final String USERPROFILE_JSP = "/WEB-INF/jsp/userprofile.jsp";
	private static final String URL = "url";
	private static final String NEWSES_OF_AUTHOR = "newses_author";
	private static final String USER = "user";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		User user= (User)session.getAttribute(USER);
		try {
			session.setAttribute(NEWSES_OF_AUTHOR, newsService.getListOfNews(user.getLogin()));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		String path = USERPROFILE_JSP;
		session.setAttribute(URL, CommandName.GO_TO_USER_PAGE.toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
